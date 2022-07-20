package com.garycoffee.user.Controller;

import com.garycoffee.user.dto.webclient.account.CreateAccountRequest;
import com.garycoffee.user.dto.webclient.account.RequestChangeBalance;
import com.garycoffee.user.dto.webclient.order.CreateOrderRequest;
import com.garycoffee.user.requestModel.Account;
import com.garycoffee.user.requestModel.Order;
import com.garycoffee.user.requestModel.Product;
import com.garycoffee.user.service.AccountService;
import com.garycoffee.user.service.OrderService;
import com.garycoffee.user.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("api/v1/staff")
@Slf4j
@Validated
public class StaffController {

    @Resource
    private AccountService accountService;

    @Resource
    private ProductService productService;

    @Resource
    private OrderService orderService;

    //Account APIs
    @PostMapping("/account")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest req){
        Account account =  accountService.createAccount(req);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/staff/account").toUriString());
        return ResponseEntity.created(uri).body(account);
    }


    @GetMapping("/account/{phone}")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Account> checkAccountBalance(
            @PathVariable String phone){
        Account account = accountService.checkAccountBalance(phone);
        return ResponseEntity.ok().body(account);
    }

    //Add account Balance
    @PutMapping("/account")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Account> addAccountBalance(
            @RequestBody RequestChangeBalance req){
        Account account = accountService.addAccountBalance(req);
        return ResponseEntity.ok().body(account);
    }


    //Product APIs
    @PostMapping("/products")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product targetProduct = productService.createProduct(product);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/staff/products").toUriString());
        return ResponseEntity.created(uri).body(targetProduct);
    }

    @GetMapping("/products/{shortName}")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Product> fetchProductByShortName(@PathVariable String shortName){
        Product product = productService.fetchProductByShortName(shortName);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<List<Product>> fetchProducts(){
        List<Product> productList = productService.fetchProducts();
        return ResponseEntity.ok().body(productList);
    }


    //Orders APIs
    @PostMapping("/orders")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest order){
        Order targetOrder = orderService.createOrder(order);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/staff/orders").toUriString());
        return ResponseEntity.created(uri).body(targetOrder);
    }

    //Get All Orders
    @GetMapping("/orders")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<List<Order>> fetchOrders(){
        List<Order> orderList = orderService.fetchOrders();
        return ResponseEntity.ok().body(orderList);
    }

    //fetch Order By Id
    @GetMapping("/orders/{id}")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<Order> fetchOrderById(@PathVariable String id){
        Order order = orderService.fetchOrderById(id);
        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/orders/search")
    @PreAuthorize("hasAnyRole('ROLE_STAFF','ROLE_ADMIN')")
    public ResponseEntity<List<Order>> fetchOrdersWithPage(
            @RequestParam (value = "phone", defaultValue = "", required = false) String phone,
            @RequestParam (value = "staffId", defaultValue = "0",required = false) Integer staffId,
            @RequestParam (value = "page", defaultValue = "1") Integer page
    ){

        List<Order> orderList = orderService.fetchOrdersWithPage(phone,staffId,page);

        return ResponseEntity.ok().body(orderList);

    }
}
