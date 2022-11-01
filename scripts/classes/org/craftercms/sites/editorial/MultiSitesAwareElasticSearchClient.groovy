package org.craftercms.sites.editorial

import org.craftercms.search.elasticsearch.impl.client.AbstractElasticsearchClientWrapper
import co.elastic.clients.elasticsearch.core.SearchRequest
import co.elastic.clients.elasticsearch.ElasticsearchClient
import java.beans.ConstructorProperties

public class MultiSitesAwareElasticSearchClient {
    public MultiSitesAwareElasticSearchClient(ElasticsearchClient client) {
        println "init method"
        println client
    }
    def search() {
        return "aaa"
    }
}