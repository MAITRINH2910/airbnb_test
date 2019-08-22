package com.airbnb.demo.controllers;

import com.airbnb.demo.DTO.request.EditPassword;
import com.airbnb.demo.DTO.response.MessageResponse;
import com.airbnb.demo.entities.User;
import com.airbnb.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> userList() {
        List<User> userList = userService.findAllUsers();
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> userHouse(@PathVariable long id) {
        User user = this.userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/user/edit-account/change-password/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity updateUserPass(@PathVariable(value = "id") Long id, @RequestBody EditPassword editPass) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        String oldPassword = editPass.getOldPass();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return new ResponseEntity(new MessageResponse("Nhập sai password cũ!"), HttpStatus.BAD_REQUEST);
        }
        String newPassword = editPass.getNewPass();
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userService.save(user);
        return new ResponseEntity(new MessageResponse("Bạn đã đổi password thành công"), HttpStatus.OK);
    }

}
