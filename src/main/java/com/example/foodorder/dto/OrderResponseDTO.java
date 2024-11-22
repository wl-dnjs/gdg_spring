package com.example.foodorder.dto;

public record OrderResponseDTO(Long id, String foodName, String userName, int quantity, String orderOption) {
}