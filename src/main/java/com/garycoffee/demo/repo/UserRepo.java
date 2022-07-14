package com.garycoffee.demo.repo;


import com.garycoffee.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    void deleteByUsername(String username);
}
