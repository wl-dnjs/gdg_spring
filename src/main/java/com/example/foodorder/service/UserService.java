package com.example.foodorder.service;

import com.example.foodorder.dto.UserRequestDTO;
import com.example.foodorder.dto.UserResponseDTO;
import com.example.foodorder.entity.User;
import com.example.foodorder.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        User user = new User(userRequestDTO.name());
        userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getUserName());
    }
}
