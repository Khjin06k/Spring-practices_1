package com.example.Coffee.dto;

import com.example.Member.entity.Member;
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
