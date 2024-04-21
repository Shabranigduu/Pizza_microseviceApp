package com.example.logistic.client;

import com.example.logistic.model.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class Client {
    protected final RestTemplate rest;
    private final String PATH = "http://order-management:8081/order/set_state/";

//    @Autowired
//    public Client(@Value("${order-server.url}") String serverUrl, RestTemplateBuilder builder) {
//        this.rest = builder
//                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
//                .build();
//    }

    public void sendStatusOrder(Integer orderId, String state) {

        URI uri = UriComponentsBuilder.fromHttpUrl(PATH+orderId)
                .queryParam("state", state) // здесь меняете на свои параметры
                .build().encode().toUri();
        ResponseEntity<OrderDTO> responseEntity = rest.postForEntity(uri, null, OrderDTO.class);

        if (responseEntity.getStatusCode().is5xxServerError()) {
            throw new RuntimeException("Ошибка запроса смены статуса заказа");
        }
    }
}
