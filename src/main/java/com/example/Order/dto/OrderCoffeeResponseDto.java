package com.example.Order.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCoffeeResponseDto {
    private long coffeeId;
    private Integer quantity;
    private String korName;
    private String engName;
    private int price;
}
