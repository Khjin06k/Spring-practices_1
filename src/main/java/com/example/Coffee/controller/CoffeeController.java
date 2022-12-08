package com.example.Coffee.controller;

import com.example.Coffee.dto.CoffeePatchDto;
import com.example.Coffee.dto.CoffeePostDto;
import com.example.Coffee.dto.CoffeeResponseDto;
import com.example.Coffee.entity.Coffee;
import com.example.Coffee.mapper.CoffeeMapper;
import com.example.Coffee.service.CoffeeService;
import com.example.Member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/coffees")
@Valid
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;

    //의존성 주입
    public CoffeeController(CoffeeService coffeeService, CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }

    //커피 주문
    @PostMapping
    public ResponseEntity postCoffee(@RequestBody @Valid CoffeePostDto coffeePostDto){

        Coffee coffee = mapper.CoffeePostDtoToCoffee(coffeePostDto);
        Coffee response = coffeeService.createCoffee(coffee);

        return new ResponseEntity<>(mapper.CoffeeToCoffeeResponseDto(response), HttpStatus.CREATED);
     }

     //커피 정보 수정
     @PatchMapping("/{coffee-id}")
     public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                       @RequestBody @Valid CoffeePatchDto coffeePatchDto){
        Coffee coffee = mapper.CoffeePatchDtoToCoffee(coffeePatchDto);
        Coffee response = coffeeService.updateCoffee(coffee);

        return new ResponseEntity<>(mapper.CoffeeToCoffeeResponseDto(response), HttpStatus.OK);
     }

    //단일 커피 조회
    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") @Positive long coffeeId){
        Coffee response = coffeeService.findCoffee(coffeeId);

        return new ResponseEntity<>(mapper.CoffeeToCoffeeResponseDto(response) ,HttpStatus.OK);
    }

    //모든 커피 조회
    @GetMapping
    public ResponseEntity getCoffees(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        Page<Coffee> pageOrders = coffeeService.findCoffees(page - 1, size);
        List<Coffee> coffees = pageOrders.getContent();

        List<CoffeeResponseDto> response = coffees.stream()
                .map(coffee -> mapper.CoffeeToCoffeeResponseDto(coffee))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //커피 정보 삭제
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
