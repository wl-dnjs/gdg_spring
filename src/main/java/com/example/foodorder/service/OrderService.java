package com.example.foodorder.service;

import com.example.foodorder.dto.OrderRequestDTO;
import com.example.foodorder.dto.OrderResponseDTO;
import com.example.foodorder.entity.Order;
import com.example.foodorder.entity.Food;
import com.example.foodorder.entity.User;
import com.example.foodorder.repository.OrderRepository;
import com.example.foodorder.repository.UserRepository;
import com.example.foodorder.repository.FoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    // 생성자 주입
    public OrderService(OrderRepository orderRepository, FoodRepository foodRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
    }

    // Food 조회
    public Food findFoodById(Long foodId) {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new EntityNotFoundException("Not founded: " + foodId)); // 예외 처리
    }

    // User 조회
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not founded: " + userId)); // 예외 처리
    }

    // 주문 추가
    @Transactional
    public OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO, User user) {
        Food food = findFoodById(orderRequestDTO.foodId());
        Order order = new Order(food, user, orderRequestDTO.quantity(), orderRequestDTO.orderOption());
        Order savedOrder = orderRepository.save(order);

        return new OrderResponseDTO(
                savedOrder.getId(),
                savedOrder.getFood().getFoodName(),
                savedOrder.getUser().getUserName(),
                savedOrder.getQuantity(),
                savedOrder.getOrderOption()
        );
    }

    // 주문 조회
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getFood().getFoodName(),
                        order.getUser().getUserName(),
                        order.getQuantity(),
                        order.getOrderOption()))
                .toList();
    }

    // 주문 변경
    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (orderRequestDTO.foodId() != null) {
            order.setFood(findFoodById(orderRequestDTO.foodId()));
        }
        if (orderRequestDTO.userId() != null) {
            order.setUser(findUserById(orderRequestDTO.userId()));
        }
        if (orderRequestDTO.quantity() > 0) {
            order.setQuantity(orderRequestDTO.quantity());
        }
        if (orderRequestDTO.orderOption() != null) {
            order.setOrderOption(orderRequestDTO.orderOption());
        }
        Order updatedOrder = orderRepository.save(order);

        return new OrderResponseDTO(
                updatedOrder.getId(),
                updatedOrder.getFood().getFoodName(),
                updatedOrder.getUser().getUserName(),
                updatedOrder.getQuantity(),
                updatedOrder.getOrderOption());
    }

    // 주문 취소
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}