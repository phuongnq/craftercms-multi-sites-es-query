import org.craftercms.search.elasticsearch.impl.client.AbstractElasticsearchClientWrapper

class MultiSitesAwareElasticSearchClient extends AbstractElasticsearchClientWrapper {
    @Override
    protected void updateIndex(SearchRequest request, Map<String, Object> parameters, RequestUpdates updates) {
        super.updateIndex(request, parameters, updates);   
    }
}