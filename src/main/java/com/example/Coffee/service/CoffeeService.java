package com.example.Coffee.service;

import com.example.Coffee.entity.Coffee;

import java.util.List;

public class CoffeeService {

    public Coffee createCoffee(Coffee coffee){
        Coffee createdCoffee = coffee;
        return createdCoffee;
    }

    public Coffee updateCoffee(Coffee coffee){
        Coffee updatedCoffee = coffee;
        return updatedCoffee;
    }

    public Coffee findCoffee(long memberId){
        Coffee coffee = new Coffee(0L, "바닐라 라떼", "Vanilla Latte", 6000);
        return coffee;
    }

    public List<Coffee> findCoffees(){
        List<Coffee> coffees = List.of(
                new Coffee(0L, "바닐라 라떼", "Vanilla Latte", 6000),
                new Coffee(1L, "카페 라떼", "Caffee Latte", 5000)
        );

        return coffees;
    }

    public void deleteCoffee(long coffeeId){
    }

}
