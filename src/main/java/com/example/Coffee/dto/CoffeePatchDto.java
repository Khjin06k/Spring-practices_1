package com.example.Coffee.dto;

import com.example.Coffee.entity.Coffee;
import com.example.NotSpace;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

public class CoffeePatchDto {
    private long coffeeId;
    @Pattern(regexp = "^[a-zA-Z]+(\\s?[a-zA-Z]+)*$")
    private String engName;
    @NotSpace
    private String korName;
    @Range(min = 100, max = 50000)
    private int price;
    private Coffee.CoffeeStatus coffeeStatus;

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

