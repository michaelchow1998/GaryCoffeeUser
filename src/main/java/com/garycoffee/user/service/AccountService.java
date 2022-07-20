package com.garycoffee.user.service;

import com.garycoffee.user.dto.webclient.account.CreateAccountRequest;
import com.garycoffee.user.dto.webclient.account.RequestChangeBalance;
import com.garycoffee.user.requestModel.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Account createAccount(CreateAccountRequest req){
        String uri = "http://localhost:8070/api/v1/accounts/";
        Account account = webClientBuilder.build()
                .post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(req), CreateAccountRequest.class)
                .retrieve()
                .bodyToMono(Account.class)
                .block();

        return account;
    }


    public Account checkAccountBalance(String phone){
        String uri = "http://localhost:8070/api/v1/accounts/" + phone;
        Account account = webClientBuilder.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Account.class)
                .block();

        return account;
    }

    public Account addAccountBalance(RequestChangeBalance req){
        String uri = "http://localhost:8070/api/v1/accounts/addBalance";
        Account account = webClientBuilder.build()
                .put()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(req), CreateAccountRequest.class)
                .retrieve()
                .bodyToMono(Account.class)
                .block();

        return account;
    }




}
