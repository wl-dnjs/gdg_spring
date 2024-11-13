package com.example.foodorder.dto;

// record를 통해 자동으로 필드와 메서드 생성됨. 클라이언트와 데이터 주고받기 위한 클래스
public record OrderDTO(Long id, String foodName, int quantity, String option) {
}
