package com.example.Coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoffeeResponseDto {
    private long coffeeId;
    private String engName;
    private String korName;
    private int price;
}
