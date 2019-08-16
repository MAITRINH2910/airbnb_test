package com.airbnb.demo.services;

import com.airbnb.demo.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findByUsername(String username);

    User findById(Long id);

    User save(User user);

}
