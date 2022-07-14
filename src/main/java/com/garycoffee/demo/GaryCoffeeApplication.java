package com.garycoffee.demo;

import com.garycoffee.demo.Enum.Sex;
import com.garycoffee.demo.model.User;
import com.garycoffee.demo.requestValid.RequestUserCreate;
import com.garycoffee.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class GaryCoffeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaryCoffeeApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            Date now = new Date();
//            userService.register(new RequestUserCreate("joe", "1234", "Joe", "Lee" , "joe@gmail.com", "12234567",Sex.M,"test"));
//            userService.register(new RequestUserCreate("gary", "1234", "Gary", "Chan" , "gary@gmail.com", "45757252",Sex.M,"test"));
//            userService.register(new RequestUserCreate("mary", "1234", "Mary", "Mak" , "mak@gmail.com", "54652125",Sex.F,"test"));
//        };
//    }

}
