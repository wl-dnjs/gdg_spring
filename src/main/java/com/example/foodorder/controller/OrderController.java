package com.example.foodorder.controller;

import com.example.foodorder.dto.OrderRequestDTO;
import com.example.foodorder.dto.OrderResponseDTO;
import com.example.foodorder.entity.Order;
import com.example.foodorder.entity.User;
import com.example.foodorder.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders") // 주문 관련 URL
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 전체 주문 목록 조회
    @GetMapping
    public List<OrderResponseDTO> getAllOrders() { // 주문을 리스트 형식으로 반환
        return orderService.getAllOrders();
    }

    // 내 주문 목록 조회
    @GetMapping("/myOrders")
    public ResponseEntity<?> getMyOrders(HttpSession session) {
        User user = (User) session.getAttribute("user");

//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User is not logged in."));
//        }

        List<Order> orders = orderService.getOrdersByUser(user);

        if (orders.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "Order not found"));
        }

        return ResponseEntity.ok(orders);
    }

    // 주문 추가
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addOrder(@RequestBody OrderRequestDTO orderRequestDTO, HttpSession session) {
        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User is not logged in."));
//        }
        OrderResponseDTO orderResponseDTO = orderService.addOrder(orderRequestDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    // 주문 변경
    @PutMapping("/{id}")
    public OrderResponseDTO updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequestDTO orderRequestDTO) { //경로에 있는 {id} 값을 파라미터 id로 바인딩함
        return orderService.updateOrder(id, orderRequestDTO);
    }

    // 주문 취소
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable("id") Long id) {
        orderService.cancelOrder(id);
    }
}