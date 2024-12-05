package com.example.foodorder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // 하나의 음식과 연결됨
    @JoinColumn(name = "food_id", nullable = false) // 외래 키(food_id)로 연결
    private Food food;

    @ManyToOne(optional = false) // 하나의 사용자와 연결됨
    @JoinColumn(name = "user_id", nullable = false) // 외래 키(user_id)로 연결
    private User user;

    private int quantity;

    private String orderOption;

    public Order() {
    }

    public Order(Food food, User user, int quantity, String orderOption) {
        this.food = food;
        this.user = user;
        this.quantity = quantity;
        this.orderOption = orderOption;
    }

    public Long getId() {
        return id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderOption() {
        return orderOption;
    }

    public void setOrderOption(String option) {
        this.orderOption = option;
    }
}