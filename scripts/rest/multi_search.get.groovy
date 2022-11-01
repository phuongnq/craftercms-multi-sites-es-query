import co.elastic.clients.elasticsearch.core.SearchRequest
import org.craftercms.engine.service.context.SiteContext

def sites = params["sites"] ? params["sites"] : [SiteContext.getCurrent().getSiteName()]
def start = params.start ? params.start as Integer : 0
def rows = params.rows ? params.rows as Integer : 10

def multiSitesAwareElasticSearchClient = applicationContext.multiSitesAwareElasticSearchClient

def ARTICLE_CONTENT_TYPE = '/page/article'

// def sites = ['editorial-b', 'editorial-c', 'editorial-d']
def indexes = sites.collect { modePreview ? "${it}-preview" : it }

SearchRequest request = SearchRequest.of(r -> r
      .query(q -> q
        .match(m -> m
            .field('content-type')
              .query(v -> v
                .stringValue(ARTICLE_CONTENT_TYPE)
            )
        )
      )
      .from(start)
      .size(rows)
      .index(indexes.join(','))
)


def result = multiSitesAwareElasticSearchClient.search(request, Map)

return processUserSearchResults(result)


private def processUserSearchResults(result) {
    def articles = []
    def hits = result.hits().hits()

    if (hits) {
      hits.each {hit ->
        def doc = hit.source()
        def article = [:]
            article.index = hit.index()
            article.id = doc.objectId
            article.objectId = doc.objectId
            article.path = doc.localId
            article.title = doc.title_t
            article.url = urlTransformationService.transform("storeUrlToRenderUrl", doc.localId)

        if (hit.highlight()) {
          def articleHighlights = hit.highlight().values()
          if (articleHighlights) {
              def highlightValues = []

              articleHighlights.each { value ->
                  highlightValues.addAll(value)
              }

              article.highlight = StringUtils.join(highlightValues, "... ")
              article.highlight = StringUtils.strip(article.highlight)
          }
        }

        articles << article
      }
    }

    return articles
  }