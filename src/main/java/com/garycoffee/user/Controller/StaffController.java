package com.garycoffee.user.Controller;

import com.garycoffee.user.dto.webclient.account.CreateAccountRequest;
import com.garycoffee.user.dto.webclient.account.RequestChangeBalance;
import com.garycoffee.user.requestModel.Account;
import com.garycoffee.user.service.AccountService;
import com.garycoffee.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
@Slf4j
@Validated
public class StaffController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public Account createAccount(@RequestBody CreateAccountRequest req){
        return accountService.createAccount(req);
    }

    @GetMapping("/account/{phone}")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public Account checkAccountBalance(
            @PathVariable String phone){
        return accountService.checkAccountBalance(phone);
    }

    @PutMapping("/account")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public Account addAccountBalance(
            @RequestBody RequestChangeBalance req){
        return accountService.addAccountBalance(req);
    }

}
