package com.garycoffee.user.service;

import com.garycoffee.user.dto.webclient.product.RequestUpdateList;
import com.garycoffee.user.dto.webclient.product.RequestUpdateProduct;
import com.garycoffee.user.requestModel.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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

        return webClientBuilder.build()
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(product), Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    //Get a Product by shortName
    public Product fetchProductByShortName(String shortName){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/products/" + shortName;
        log.info(uri);

        return webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    //Get All Products
    public List<Product> fetchProducts(){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/products";
        return webClientBuilder.build()
                .get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>(){})
                .block();
    }

    public List<Product> refillProducts(RequestUpdateList requestUpdateList){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/products";

        return webClientBuilder.build()
                .put()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestUpdateList), Product.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>(){})
                .block();
    }

    public Product refillSingleProduct(RequestUpdateProduct requestUpdateProduct){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/products/"+requestUpdateProduct.getShortName();

        return webClientBuilder.build()
                .put()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestUpdateProduct), Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }


    public String deleteProduct(String shortName){
        String uri = "https://gary-coffee-orders.herokuapp.com/api/v1/products/" +shortName;
        return webClientBuilder.build()
                .delete()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

}
