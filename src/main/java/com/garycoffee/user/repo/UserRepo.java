package com.garycoffee.user.repo;


import com.garycoffee.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByPhone(String phone);
    void deleteByUsername(String username);
}
