package com.example.Order.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;
@Getter
public class OrderCoffeeDto {
    @Positive
    private long coffeeId;

    @Positive
    private int quantity;
}
