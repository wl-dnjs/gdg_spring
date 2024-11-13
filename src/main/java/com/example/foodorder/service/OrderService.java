package com.example.foodorder.service;

import com.example.foodorder.dto.OrderDTO;
import com.example.foodorder.entity.Order;
import com.example.foodorder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    // 생성자. orderRepository 필드 초기화
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 주문 추가
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.foodName(), orderDTO.quantity(), orderDTO.option()); // orderDTO 필드로 order 객체 생성
        order = orderRepository.save(order); // save 메서드 사용해서 order 객체 데베에 저장하고 다시 order에 저장
        return new OrderDTO(order.getId(), order.getFoodName(), order.getQuantity(), orderDTO.option()); // 데베 order에서 저장된 정보로 다시 orderDTO 객체 생성해서 반환
    }

    // 주문 조회
    public List<OrderDTO> getAllOrders() { // OrderDTO 객체의 리스트 반환
        return orderRepository.findAll().stream() // 모든 order 엔티티 리스트로 가져와서 스트림으로 변환
                .map(order -> new OrderDTO(order.getId(), order.getFoodName(), order.getQuantity(), order.getOption())) // order 객체를 orderDTO 객체로 변환함
                .toList(); // 변환된 orderDTO 객체를 리스트로 만들어 반환
    }

    // 주문 변경
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(); // id로 order 객체 조회. 해당 id 없으면 예외 발생
        if (orderDTO.quantity() > 0) { // 입력한 수량이 0보다 크면 변경
            order.setQuantity(orderDTO.quantity());
        }
        if (orderDTO.option() != null) { // 입력한 옵션이 null이 아니면 변경
            order.setOption(orderDTO.option());
        }
        order = orderRepository.save(order); // 변경된 order 객체 데베에 저장
        return new OrderDTO(order.getId(), order.getFoodName(), order.getQuantity(), order.getOption()); //데베 order에서 저장된 정보로 다시 orderDTO 객체 생성해서 반환
    }

    // 주문 취소
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }
}