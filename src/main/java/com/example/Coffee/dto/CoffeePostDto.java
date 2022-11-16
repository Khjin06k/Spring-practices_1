package com.example.Coffee.dto;

import com.example.NotSpace;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

public class CoffeePostDto {
    private long coffeeId;

    @Pattern(regexp = "^[a-zA-Z]+(\\s?[a-zA-Z]+)*$")
    private String engName;
    @NotSpace
    private String korName;
    @Range(min = 100, max = 50000)
    private int price;

    public long getCoffeeId() {
        return coffeeId;
    }

    public String getEngName() {
        return engName;
    }

    public String getKorName() {
        return korName;
    }

    public int getPrice() {
        return price;
    }
}
