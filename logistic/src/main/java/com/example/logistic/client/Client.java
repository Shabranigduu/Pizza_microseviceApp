package com.example.logistic.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class Client {
    protected final RestTemplate rest;
    private final String PATH = "/order/{state}";

    @Autowired
    public Client(@Value("${order-server.url}") String serverUrl, RestTemplateBuilder builder) {
        this.rest = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .build();
    }

    public void sendStatusOrder(String state) throws Throwable {
        ResponseEntity<Void> responseEntity = rest.getForEntity(PATH, Void.class, state);

        if (responseEntity.getStatusCode().is5xxServerError()) {
            throw new Throwable("Ошибка запроса смены статуса заказа");
        }
    }
}
