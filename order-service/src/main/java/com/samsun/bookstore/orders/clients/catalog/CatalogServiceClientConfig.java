package com.samsun.bookstore.orders.clients.catalog;

import com.samsun.bookstore.orders.ApplicationProperties;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class CatalogServiceClientConfig {
    //    without any Resilience patter configuration added.
    //    @Bean
    //    RestClient restClient(ApplicationProperties properties) {
    //        return RestClient.builder().baseUrl(properties.catalogServiceUrl()).build();
    //    }

    //    Below is deprecated as of Spring 4.0
    //    @Bean
    //    RestClient restClient(ApplicationProperties properties) {
    //        return RestClient
    //                .builder()
    //                .baseUrl(properties.catalogServiceUrl())
    //                .requestFactory(ClientHttpRequestFactories
    //                        .get(ClientHttpRequestFactorySettings.DEFAULTS
    //                                .withConnectTimeout(Duration.ofSeconds(5))
    //                                .withReadTimeout(Duration.ofSeconds(5))))
    //                .build();
    //    }

    @Bean
    RestClient restClient(ApplicationProperties properties) {

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000, java.util.concurrent.TimeUnit.MILLISECONDS)
                .setResponseTimeout(5000, java.util.concurrent.TimeUnit.MILLISECONDS)
                .build();

        CloseableHttpClient httpClient =
                HttpClients.custom().setDefaultRequestConfig(config).build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return RestClient.builder()
                .baseUrl(properties.catalogServiceUrl())
                .requestFactory(factory)
                .build();
    }
}
