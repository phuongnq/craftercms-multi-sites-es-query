package org.craftercms.sites.editorial

import org.craftercms.search.elasticsearch.impl.client.AbstractElasticsearchClientWrapper
import co.elastic.clients.elasticsearch.core.SearchRequest
import co.elastic.clients.elasticsearch.ElasticsearchClient
import java.beans.ConstructorProperties

class MultiSitesAwareElasticSearchClient extends AbstractElasticsearchClientWrapper {
    protected String indexIdFormat
    
    @ConstructorProperties(["client", "indexIdFormat"])
    public MultiSitesAwareElasticSearchClient(ElasticsearchClient client, String indexIdFormat) {
        super(client)
        this.indexIdFormat = indexIdFormat
    }
    
    @Override
    protected void updateIndex(SearchRequest request, Map<String, Object> parameters, RequestUpdates updates) {
        super.updateIndex(request, parameters, updates)
        
        List<String> currentIndices = updates.getIndex();
        
        println currentIndices
    }
    
    @Override
    protected void updateQuery(SearchRequest request, Map<String, Object> parameters, RequestUpdates updates) {
        super.updateQuery(request, parameters, updates)
    }
}