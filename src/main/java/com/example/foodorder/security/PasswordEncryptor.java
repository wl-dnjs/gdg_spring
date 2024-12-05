package com.example.foodorder.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public String encryptPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }
    public boolean matches(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }
}
