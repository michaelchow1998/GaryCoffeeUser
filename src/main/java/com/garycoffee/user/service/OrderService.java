package com.garycoffee.user.service;

import com.garycoffee.user.dto.webclient.order.CreateOrderRequest;
import com.garycoffee.user.requestModel.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Order createOrder(CreateOrderRequest order){
        String uri = "http://localhost:8090/api/v1/orders";
        Order targetOrder = webClientBuilder.build()
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(order), Order.class)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        return targetOrder;
    }

    //Get All Orders
    public List<Order> fetchOrders(){
        String uri = "http://localhost:8090/api/v1/orders";
        List orderList = webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return orderList;
    }

    public Order fetchOrderById(String id){
        String uri = "http://localhost:8090/api/v1/orders/" + id;
        Order order = webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Order.class)
                .block();

        return order;
    }

    public List<Order> fetchOrdersWithPage(String phone, Integer staffId, Integer page){

        String uri = processUri(phone,staffId,page);
        log.info(uri);

        List orderList = webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return orderList;
    }

    public String processUri(String phone, Integer staffId, Integer page){
        String uri = "";
        if(!phone.isBlank() | !staffId.equals(0)){
            if (!phone.isBlank()) {
                uri = "http://localhost:8090/api/v1/orders" +
                        "?phone="+phone +"&page=" + page;

            }
            if (!staffId.equals(0)) {
                uri = "http://localhost:8090/api/v1/orders" +
                        "?staffId="+staffId +"&page=" + page;
            }
            if(!phone.isBlank() & !staffId.equals(0)){
                uri = "http://localhost:8090/api/v1/orders" +
                        "?phone="+phone +"&page=" + page;
            }
        }else{
            throw new RuntimeException("param format wrong");
        }

        return uri;

    }
}
