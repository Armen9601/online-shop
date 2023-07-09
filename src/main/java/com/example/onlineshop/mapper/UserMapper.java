package com.example.onlineshop.mapper;

import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
