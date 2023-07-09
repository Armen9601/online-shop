package com.example.onlineshop.controller;

import com.example.onlineshop.dto.UserAuthRequest;
import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDto userDto) {
        userService.registration(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserAuthRequest userAuthRequest) {
        return ResponseEntity.ok(userService.login(userAuthRequest));
    }
}
