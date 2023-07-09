package com.example.onlineshop.service.serviceImpl;

import com.example.onlineshop.dto.UserAuthRequest;
import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.entity.Role;
import com.example.onlineshop.entity.User;
import com.example.onlineshop.mapper.UserMapper;
import com.example.onlineshop.repo.UserRepository;
import com.example.onlineshop.service.UserService;
import com.example.onlineshop.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;
    private final UserMapper userMapper;

    @Override
    public String login(UserAuthRequest userAuthRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(), userAuthRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("user logged in");
        return tokenUtil.generateToken(userAuthRequest.getUsername());
    }

    @Override
    public void registration(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        User saved = userRepository.save(user);
        log.info("user registered: id " + saved.getId());
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("Fetching a user by username: " + username);
        return userMapper.toDto(user);
    }
}
