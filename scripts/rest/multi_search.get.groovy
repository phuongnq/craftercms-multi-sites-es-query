import co.elastic.clients.elasticsearch.core.SearchRequest


def ARTICLE_CONTENT_TYPE = '/page/article'

def sites = ['editorial-b', 'editorial-c', 'editorial-d']
def indexes = sites.collect { "${it}-authoring_v1" }

println indexes.join(',')

return indexes.join(',')

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
    //   .index(indexes.join(','))
)

def result = elasticsearchClient.search(request, Map)

return result
