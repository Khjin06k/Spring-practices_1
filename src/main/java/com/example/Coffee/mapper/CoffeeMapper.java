package com.example.Coffee.mapper;

import com.example.Coffee.dto.CoffeePatchDto;
import com.example.Coffee.dto.CoffeePostDto;
import com.example.Coffee.dto.CoffeeResponseDto;
import com.example.Coffee.entity.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee CoffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee CoffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto CoffeeToCoffeeResponseDto(Coffee coffee);
}
