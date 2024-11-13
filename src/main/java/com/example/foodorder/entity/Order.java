package com.example.foodorder.entity;

import jakarta.persistence.*;

// JPA를 통해 데베와 연동하기 위한 클래스
@Entity
@Table(name = "orders") // 연결될 테이블 이름 지정
public class Order {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동으로 아이디 생성
    private Long id;
    private String foodName;
    private int quantity;
    private String option;

    // 기본 생성자
    public Order() {
    }

    // 매개변수가 있는 생성자
    public Order(String foodName, int quantity, String option) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.option = option;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}