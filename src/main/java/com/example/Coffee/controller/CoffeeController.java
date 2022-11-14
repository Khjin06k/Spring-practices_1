package com.example.Coffee.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/coffees", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CoffeeController {
    //커피 주문
    @PostMapping
    public String postCoffee(@RequestParam("engName") String engName,
                                     @RequestParam("korName") String korName,
                                     @RequestParam("price") int price){
        System.out.println("# engName : " + engName);
        System.out.println("# korName : " + korName);
        System.out.println("# price : " + price);

        String response = "{\"" + "engName\" : \"" + engName + "\"," +
                "\"korName\" : " + korName + "\", \"" +
                "\"price\" : \"" + price + "\"}";

        return response;
    }

    //단일 커피 조회
    @GetMapping("/{coffee-id}")
    public String getCoffee(@PathVariable("coffee-id") long coffeeId){
        System.out.println("# coffeeId : " + coffeeId);

        return null;
    }

    //모든 커피 조회
    @GetMapping
    public String getCoffees(){
        System.out.println("# get Coffees");

        return null;
    }

}
