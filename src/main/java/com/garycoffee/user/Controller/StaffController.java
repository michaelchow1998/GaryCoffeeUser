package com.garycoffee.user.Controller;

import com.garycoffee.user.requestValid.RequestChangeBalance;
import com.garycoffee.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/staff")
@RequiredArgsConstructor
@Slf4j
@Validated
public class StaffController {

    @Autowired
    private UserService userService;

    //PUT - ADD Balance to User
    @PutMapping("/addBalance")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Integer> addBalance
            (@RequestBody @Valid RequestChangeBalance req){
        Integer afterBalance = userService.addBalance(req.getPhone(), req.getAmount());
        return ResponseEntity.ok().body(afterBalance);
    }

    //PUT - REDUCE Balance to User
    @PutMapping("/reduceBalance")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Integer> reduceBalance
            (@RequestBody @Valid RequestChangeBalance req){
        Integer afterBalance = userService.reduceBalance(req.getPhone(), req.getAmount());
        return ResponseEntity.ok().body(afterBalance);
    }
}
