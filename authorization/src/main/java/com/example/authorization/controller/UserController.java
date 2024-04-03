package com.example.authorization.controller;

import com.example.authorization.model.dto.RequestUserDto;
import com.example.authorization.model.dto.ResponseUserDto;
import com.example.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseUserDto postUser(@RequestBody @Valid RequestUserDto requestUserDto) {
        return userService.postUser(requestUserDto);
    }

    @GetMapping("/{userId}")
    public ResponseUserDto getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }
    
    @PatchMapping("/{userId}")
    public ResponseUserDto patchUser(@PathVariable Integer userId, @RequestBody RequestUserDto requestUserDto) {
        return userService.patchUser(userId, requestUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }
}
