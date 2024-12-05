package com.example.foodorder.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.foodorder.dto.UserRequestDTO;
import com.example.foodorder.dto.UserResponseDTO;
import com.example.foodorder.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/users") // 유저 관련 URL
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDTO userRequestDTO, HttpSession session) {
        try {
            UserResponseDTO userResponseDTO = userService.addUser(userRequestDTO);
            userService.login(userRequestDTO.email(), userRequestDTO.password(), session);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message",e.getMessage()));
        }
    }
}
