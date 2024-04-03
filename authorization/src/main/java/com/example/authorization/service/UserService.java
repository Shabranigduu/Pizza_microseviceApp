package com.example.authorization.service;

import com.example.authorization.model.dto.RequestUserDto;
import com.example.authorization.model.dto.ResponseUserDto;

public interface UserService {
    ResponseUserDto postUser(RequestUserDto requestUserDto);
    ResponseUserDto getUser(Integer userId);
    ResponseUserDto patchUser(Integer userId, RequestUserDto requestUserDto);
    void deleteUser(Integer userId);
}
