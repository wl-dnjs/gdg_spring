package com.example.foodorder.controller;

import com.example.foodorder.dto.OrderDTO;
import com.example.foodorder.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; // RESTFul한 API를 위해 HTTP 상태 코드 관련 클래스 추가

import java.util.List;

@RestController
@RequestMapping("/api/orders") // 기본 경로
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 목록 조회
    @GetMapping
    public List<OrderDTO> getAllOrders() { // 주문을 리스트 형식으로 반환
        return orderService.getAllOrders();
    }

    // 주문 추가
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // 201 Created 상태 코드 반환. 성공적으로 생성됨을 뜻함
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {  // @RequestBody를 사용하여 사용자의 JSON 데이터를 orderDTO로 매핑함
        return orderService.addOrder(orderDTO);
    }

    // 주문 변경
    @PutMapping("/{id}") // 특정 주문 변경해야 하니까 {id} 필요
    public OrderDTO updateOrder(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO) { //경로에 있는 {id} 값을 파라미터 id로 바인딩함
        return orderService.updateOrder(id, orderDTO);
    }

    // 주문 취소
    @DeleteMapping("/{id}") // 특정 주문 삭제해야 하니까 {id} 필요
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content 상태 코드 반환. 성공적으로 삭제되고 반환할 데이터는 없음을 뜻함
    public void cancelOrder(@PathVariable("id") Long id) { //경로에 있는 {id} 값을 파라미터 id로 바인딩함
        orderService.cancelOrder(id);
    }
}