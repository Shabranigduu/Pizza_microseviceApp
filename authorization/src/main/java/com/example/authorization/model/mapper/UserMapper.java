package com.example.authorization.model.mapper;

import com.example.authorization.model.User;
import com.example.authorization.model.dto.RequestUserDto;
import com.example.authorization.model.dto.ResponseUserDto;

public class UserMapper {
    public static User toEntity(RequestUserDto userDto) {
        return new User(userDto.getName(), userDto.getAddress());
    }

    public static ResponseUserDto toResponseDto(User user) {
        return new ResponseUserDto(user.getId(), user.getName(), user.getAddress());
    }
}
