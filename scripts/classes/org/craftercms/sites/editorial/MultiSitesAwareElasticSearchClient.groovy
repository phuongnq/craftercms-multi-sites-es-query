package org.craftercms.sites.editorial

import org.craftercms.search.elasticsearch.impl.client.AbstractElasticsearchClientWrapper
import co.elastic.clients.elasticsearch.core.SearchRequest
import co.elastic.clients.elasticsearch.ElasticsearchClient
import java.beans.ConstructorProperties

class MultiSitesAwareElasticSearchClient extends AbstractElasticsearchClientWrapper {
    @ConstructorProperties({"client"})
    public SiteAwareElasticsearchClient(ElasticsearchClient client) {
        super(client)
    }
    @Override
    protected void updateIndex(SearchRequest request, Map<String, Object> parameters, RequestUpdates updates) {
        super.updateIndex(request, parameters, updates)   
    }
}