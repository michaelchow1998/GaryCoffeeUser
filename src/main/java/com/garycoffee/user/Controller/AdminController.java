package com.garycoffee.user.Controller;

import com.garycoffee.user.dto.webclient.log.ProductLog;
import com.garycoffee.user.dto.webclient.log.UserLog;
import com.garycoffee.user.requestModel.Account;
import com.garycoffee.user.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@Slf4j
@Validated
public class AdminController {

    @Resource
    private AdminService adminService;

    @GetMapping("/user-log")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserLog>> fetchUserLog(){
        List<UserLog> userLogList = adminService.fetchUserLog();
        return ResponseEntity.ok().body(userLogList);
    }

    @GetMapping("/product-log")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProductLog>> fetchProductLog(){
        List<ProductLog> productLogList = adminService.fetchProductLog();
        return ResponseEntity.ok().body(productLogList);
    }

    @GetMapping("/product-log/{shortName}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<ProductLog>> fetchProductLogByShortName(@PathVariable String shortName){
        List<ProductLog> productLogList = adminService.fetchProductLogByShortName(shortName);
        return ResponseEntity.ok().body(productLogList);
    }
}
