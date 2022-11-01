package org.craftercms.sites.editorial

import org.craftercms.search.elasticsearch.impl.client.AbstractElasticsearchClientWrapper
import co.elastic.clients.elasticsearch.core.SearchRequest
import co.elastic.clients.elasticsearch.ElasticsearchClient
import java.beans.ConstructorProperties

class MultiSitesAwareElasticSearchClient extends AbstractElasticsearchClientWrapper {
    @ConstructorProperties(["client"])
    public MultiSitesAwareElasticSearchClient(ElasticsearchClient client) {
        super(client)
        println "init method"
        println client
    }
    
    def search() {
        return "bbbb"
    }
}