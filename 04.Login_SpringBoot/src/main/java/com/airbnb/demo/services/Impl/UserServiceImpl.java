package com.airbnb.demo.services.Impl;

import com.airbnb.demo.entities.User;
import com.airbnb.demo.repositories.UserRepository;
import com.airbnb.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String tenNguoiDung) {
        return userRepository.findByUsername(tenNguoiDung);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public User save(User user){
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
