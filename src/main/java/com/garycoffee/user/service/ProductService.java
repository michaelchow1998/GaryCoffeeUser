package com.garycoffee.user.service;

import com.garycoffee.user.requestModel.Product;
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
public class ProductService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    //Create Product
    public Product createProduct(Product product){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/products";
        Product targetProduct = webClientBuilder.build()
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(product), Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        return targetProduct;
    }

    //Get a Product by shortName
    public Product fetchProductByShortName(String shortName){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/products/" + shortName;
        log.info(uri);
        Product product = webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        return product;
    }

    //Get All Products
    public List<Product> fetchProducts(){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/products";
        List productList = webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return productList;
    }

}
