package com.garycoffee.demo.Controller;

import com.garycoffee.demo.model.User;
import com.garycoffee.demo.requestValid.RequestChangeBalance;
import com.garycoffee.demo.requestValid.RequestChangePw;
import com.garycoffee.demo.requestValid.RequestUserCreate;
import com.garycoffee.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/guest")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    //ADD: User register
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RequestUserCreate user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/register").toUriString());
        return ResponseEntity.created(uri).body(userService.register(user));
    }

    //PUT: UPDATE User password
    @PutMapping("/changepw")
    public ResponseEntity<User> register(@RequestBody @Valid RequestChangePw request){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/register").toUriString());
        return ResponseEntity.created(uri).body(userService.changePw(request));
    }


}
