package com.example.Order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/order")
@Valid
public class OrderController {
    //주문 등록
    @PostMapping
    public ResponseEntity postOrder(@RequestParam("memberId") @Valid long memberId,
                            @RequestParam("coffeeId") long coffeeId){
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("coffeeId", coffeeId);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    //단일 주문 조회
    @GetMapping("/{order-Id}")
    public ResponseEntity getOrder(@PathVariable("order-Id") @Valid long orderId){
        System.out.println("# orderId : " + orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //모든 주문 조회
    @GetMapping
    public ResponseEntity getOrders() {
        System.out.println("# get Orders");

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
