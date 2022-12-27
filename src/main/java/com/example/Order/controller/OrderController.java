package com.example.Order.controller;

import com.example.Member.service.MemberService;
import com.example.Order.dto.OrderPatchDto;
import com.example.Order.dto.OrderPostDto;
import com.example.Order.dto.OrderResponseDto;
import com.example.Order.entity.Order;
import com.example.Order.mapper.OrderMapper;
import com.example.Order.service.OrderService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/order")
@Valid
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final MemberService memberService;

    public OrderController(OrderService orderService,
                           OrderMapper mapper, MemberService memberService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.memberService = memberService;
    }
    //주문 등록
    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto){
        Order order = mapper.orderPostDtoToOrder(orderPostDto);

        Order response = orderService.createOrder(order);

        return new ResponseEntity<>(mapper.orderToOrderResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{order-id}")
    public ResponseEntity patchOrder(@PathVariable("order-id") @Valid long orderId,
                                     @RequestBody @Valid OrderPatchDto orderPatchDto){
        Order order = mapper.orderPatchDtoToOrder(orderPatchDto);

        Order response = orderService.updateOrder(order);

        return new ResponseEntity<>(mapper.orderToOrderResponseDto(response), HttpStatus.OK);
    }

    //단일 주문 조회
    @GetMapping("/{order-Id}")
    public ResponseEntity getOrder(@PathVariable("order-Id") @Valid long orderId){
       Order response = orderService.findOrder(orderId);

        return new ResponseEntity(mapper.orderToOrderResponseDto(response), HttpStatus.OK);
    }

    //모든 주문 조회
    @GetMapping
    public ResponseEntity getOrders(@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        Page<Order> pageOrders = orderService.findOrders(page - 1, size);
        List<Order> orders = pageOrders.getContent();

        //OrderResponseDto 클래스 작성 필요
        List<OrderResponseDto> response =
                orders.stream()
                        .map(order -> mapper.orderToOrderResponseDto(order))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
