package com.example.onlineshop.service;

import com.example.onlineshop.dto.UserAuthRequest;
import com.example.onlineshop.dto.UserDto;

public interface UserService {

    String login(UserAuthRequest userAuthRequest);

    void registration(UserDto userDto);

    UserDto findByUsername(String username);

}
