package com.example.authorization.service.impl;

import com.example.authorization.exception.NotFoundException;
import com.example.authorization.model.User;
import com.example.authorization.model.dto.RequestUserDto;
import com.example.authorization.model.dto.ResponseUserDto;
import com.example.authorization.model.mapper.UserMapper;
import com.example.authorization.repository.UserRepository;
import com.example.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseUserDto postUser(RequestUserDto requestUserDto) {
        User user = UserMapper.toEntity(requestUserDto);

        return UserMapper.toResponseDto(userRepository.save(user));
    }

    @Override
    public ResponseUserDto getUser(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) throw new NotFoundException("Пользователь с запрашиваемым id не найден");

        return UserMapper.toResponseDto(userOptional.get());
    }

    @Override
    public ResponseUserDto patchUser(Integer userId, RequestUserDto requestUserDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            throw new NotFoundException("Пользователь с запрашиваемым id не найден"); //проверку вынести в отдельный метод
        User user = userOptional.get();

        if (requestUserDto.getName() != null && !requestUserDto.getName().isBlank()) {
            user.setName(requestUserDto.getName());
        }
        if (requestUserDto.getAddress() != null && !requestUserDto.getAddress().isBlank()) {
            user.setAddress(requestUserDto.getAddress());
        }

        return UserMapper.toResponseDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
