package com.example.foodorder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.foodorder.dto.UserRequestDTO;
import com.example.foodorder.dto.UserResponseDTO;
import com.example.foodorder.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.addUser(userRequestDTO);
    }
}
