package com.example.Order.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    //주문 등록
    @PostMapping
    public String postOrder(@RequestParam("memberId") long memberId,
                            @RequestParam("coffeeId") long coffeeId){
        System.out.println("# memberId : " + memberId);
        System.out.println("# coffeeId : " + coffeeId);

        String response = "{\"memberId" + "\" : \"" + memberId +
                "\", \" coffeeId \" : " + coffeeId + "\"}" ;

        return response;
    }

    //단일 주문 조회
    @GetMapping("/{order-Id}")
    public String getOrder(@PathVariable("order-Id") long orderId){
        System.out.println("# orderId : " + orderId);

        return null;
    }

    //모든 주문 조회
    @GetMapping
    public String getOrders() {
        System.out.println("# get Orders");

        return null;
    }

}
