package com.example.Coffee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();
    @PostConstruct
    public void init(){
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }
    //커피 주문
    @PostMapping
    public ResponseEntity postCoffee(@RequestParam("engName") String engName,
                                     @RequestParam("korName") String korName,
                                     @RequestParam("price") int price){

        HashMap<String, Object> map = new HashMap<>();
        map.put("engName", engName);
        map.put("korName", korName);
        map.put("price", price);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
     }

     //커피 정보 수정
     @PatchMapping("/{coffee-id}")
     public ResponseEntity patchCoffee(@PathVariable("coffee-id") long coffeeId,
                                       @RequestParam("korName") String korName,
                                       @RequestParam("price") int price){
        Map<String, Object> map = coffees.get(coffeeId);
        map.put("korName", korName);
        map.put("price", price);

        return new ResponseEntity(map, HttpStatus.OK);
     }

    //단일 커피 조회
    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId){
        System.out.println("# coffeeId : " + coffeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //모든 커피 조회
    @GetMapping
    public ResponseEntity getCoffees(){
        System.out.println("# get Coffees");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //커피 정보 삭제
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        coffees.remove(coffeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
