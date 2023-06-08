package com.learning.locationmanagementapi.controller;

import com.learning.locationmanagementapi.exception.BusinessException;
import com.learning.locationmanagementapi.model.UserModel;
import com.learning.locationmanagementapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Boolean> login(@RequestBody UserModel userModel) throws BusinessException {
        boolean result = userService.login(userModel);
       return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<Long> registerUser(@RequestBody UserModel userModel) throws BusinessException{
        Long id = userService.register(userModel);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

}
