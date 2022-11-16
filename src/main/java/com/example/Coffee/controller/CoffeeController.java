package com.example.Coffee.controller;

import com.example.Coffee.dto.CoffeePatchDto;
import com.example.Coffee.dto.CoffeePostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/coffees")
@Valid
public class CoffeeController {
    //커피 주문
    @PostMapping
    public ResponseEntity postCoffee(@RequestBody @Valid CoffeePostDto coffeePostDto){

        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
     }

     //커피 정보 수정
     @PatchMapping("/{coffee-id}")
     public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                       @RequestBody @Valid CoffeePatchDto coffeePatchDto){
        coffeePatchDto.setCoffeeId(coffeeId);
        coffeePatchDto.setPrice(6000);

        return new ResponseEntity<>(coffeePatchDto, HttpStatus.OK);
     }

    //단일 커피 조회
    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") @Positive long coffeeId){
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
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
