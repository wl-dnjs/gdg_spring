package com.example.foodorder.repository;

import com.example.foodorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속받음 -> CRUD 메서드(save, findById, findAll, deleteById 등) 자동 제공
public interface OrderRepository extends JpaRepository<Order, Long> { //<엔티티 타입, 기본키 타입>
}