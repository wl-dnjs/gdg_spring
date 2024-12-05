package com.example.foodorder.service;

import com.example.foodorder.dto.UserRequestDTO;
import com.example.foodorder.dto.UserResponseDTO;
import com.example.foodorder.entity.User;
import com.example.foodorder.repository.UserRepository;
import com.example.foodorder.security.PasswordEncryptor;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncryptor = new PasswordEncryptor();
    }

    @Transactional
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        String encryptedPassword = passwordEncryptor.encryptPassword(userRequestDTO.password());
        User user = new User(userRequestDTO.name(), userRequestDTO.email(), encryptedPassword);
        userRepository.save(user);
        return new UserResponseDTO(user.getUserName(), user.getEmail());
    }


    public UserResponseDTO login(String email, String password, HttpSession session) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncryptor.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        session.setAttribute("user", user);

        return new UserResponseDTO(user.getEmail(), user.getUserName());
    }
}
