package com.garycoffee.user.service;

import com.garycoffee.user.dto.webclient.log.ProductLog;
import com.garycoffee.user.dto.webclient.log.UserLog;
import com.garycoffee.user.requestModel.Order;
import com.garycoffee.user.requestModel.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<UserLog> fetchUserLog(){
        String uri = "https://gary-coffee-log.herokuapp.com/api/v1/user-log";
        return webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UserLog>>(){})
                .block();
    }

    public List<ProductLog> fetchProductLog(){
        String uri = "https://gary-coffee-log.herokuapp.com/api/v1/product-log";
        return webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductLog>>(){})
                .block();
    }

    public  List<ProductLog> fetchProductLogByShortName(String shortName){
        String uri = "https://gary-coffee-log.herokuapp.com/api/v1/product-log/"+shortName;
        return webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductLog>>(){})
                .block();
    }

}
