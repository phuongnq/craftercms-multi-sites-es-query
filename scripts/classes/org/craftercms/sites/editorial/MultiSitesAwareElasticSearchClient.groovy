package org.craftercms.sites.editorial

import org.craftercms.search.elasticsearch.impl.client.AbstractElasticsearchClientWrapper
import co.elastic.clients.elasticsearch.core.SearchRequest
import co.elastic.clients.elasticsearch.ElasticsearchClient
import java.beans.ConstructorProperties

public class MultiSitesAwareElasticSearchClient extends AbstractElasticsearchClientWrapper {
    @ConstructorProperties(["client"])
    public MultiSitesAwareElasticSearchClient(ElasticsearchClient client) {
        super(client)
        println "init method"
        println client
    }
    
    @Override
    protected void updateIndex(SearchRequest request, Map<String, Object> parameters, RequestUpdates updates) {
        super.updateIndex(request, parameters, updates)   
    }
    
    @Override
    protected void updateQuery(SearchRequest request, Map<String, Object> parameters, RequestUpdates updates) {
        super.updateQuery(request, parameters, updates)
        
    }
}