package com.garycoffee.user.service;

import com.garycoffee.user.dto.webclient.order.CreateOrderRequest;
import com.garycoffee.user.requestModel.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
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
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/orders";
        try{
             return  webClientBuilder.build()
                    .post()
                    .uri(uri)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(order), Order.class)
                    .retrieve()
                    .bodyToMono(Order.class)
                    .block();
        }catch (ServiceException se){
            log.warn("Error: "+ se.getMessage());
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    //Get All Orders
    public List<Order> fetchOrders(){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/orders";
        return webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Order>>(){})
                .block();
    }

    public Order fetchOrderById(String id){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/orders/" + id;

        return webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    public Page<Order> fetchOrdersWithPage(String phone, Integer staffId, Integer page){

        String uri = processUri(phone,staffId,page);
        log.info(uri);

        return webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Page<Order>>(){})
                .block();
    }



    public String processUri(String phone, Integer staffId, Integer page){
        String uri = "";
        if(!phone.isEmpty() | !staffId.equals(0)){
            if (!phone.isEmpty()) {
                uri = "https://gary-coffee-orders.herokuapp.com/api/v1/orders" +
                        "?phone="+phone +"&page=" + page;

            }
            if (!staffId.equals(0)) {
                uri = "https://gary-coffee-orders.herokuapp.com/api/v1/orders" +
                        "?staffId="+staffId +"&page=" + page;
            }
            if(!phone.isEmpty() & !staffId.equals(0)){
                uri = "https://gary-coffee-orders.herokuapp.com/api/v1/orders" +
                        "?phone="+phone +"&page=" + page;
            }
        }else{
            throw new RuntimeException("param format wrong");
        }

        return uri;

    }
}
