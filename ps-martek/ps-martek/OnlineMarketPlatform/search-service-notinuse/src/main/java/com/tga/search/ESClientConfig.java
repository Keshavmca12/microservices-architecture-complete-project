package com.tga.search;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.tga.search.data.es.repository")
@ComponentScan(basePackages = { "com.tga.search" })
@Slf4j
public class ESClientConfig {

    @Value("${es_service_url}")
    private String esHostName;
    @Value("${es_userName}")
    private String esUserName;
    @Value("${es_password}")
    private String esPassword;

    @Bean
    public RestHighLevelClient client() {
        log.info("esHostName: "+ esHostName);
        if(esHostName == null) {
            log.error("Elastic Search Service URL is not configured");
            throw new RuntimeException("Elastic Search Service URL is not configured");
        }
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo(this.esHostName)
                .withBasicAuth(this.esUserName, this.esPassword)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}