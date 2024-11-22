package com.example.foodorder.dto;

public record OrderRequestDTO(Long foodId, Long userId, int quantity, String orderOption) {
}
