import co.elastic.clients.elasticsearch.core.SearchRequest

def multiSitesAwareElasticSearchClient = applicationContext.multiSitesAwareElasticSearchClient

def ARTICLE_CONTENT_TYPE = '/page/article'

def sites = ['editorial-b', 'editorial-c', 'editorial-d']
def indexes = sites.collect { "${it}-authoring_v1" }

SearchRequest request = SearchRequest.of(r -> r
      .query(q -> q
        .match(m -> m
            .field('content-type')
              .query(v -> v
                .stringValue(ARTICLE_CONTENT_TYPE)
            )
        )
      )
      .from(0)
      .size(10)
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