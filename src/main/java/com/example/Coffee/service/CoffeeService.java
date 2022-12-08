package com.example.Coffee.service;

import com.example.Coffee.entity.Coffee;
import com.example.Coffee.repository.CoffeeRepository;
import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public Coffee createCoffee(Coffee coffee){
        //Coffee createdCoffee = coffee;
       // return createdCoffee;
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();

        verifyExistCoffee(coffeeCode);
        coffee.setCoffeeCode(coffeeCode);

        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Coffee coffee){
        //Coffee updatedCoffee = coffee;
        //return updatedCoffee;
        Coffee findCoffee = findVerifiedCoffee(coffee.getCoffeeId());

        Optional.ofNullable(coffee.getKorName())
                .ifPresent(korName -> findCoffee.setKorName(korName));
        Optional.ofNullable(coffee.getEngName())
                .ifPresent(engName -> findCoffee.setEngName(engName));
        Optional.ofNullable(coffee.getPrice())
                .ifPresent(price -> findCoffee.setPrice(price));

        Optional.ofNullable(coffee.getCoffeeStatus())
                .ifPresent(coffeeStatus -> findCoffee.setCoffeeStatus(coffeeStatus));

        return coffeeRepository.save(findCoffee);
    }

    public Coffee findCoffee(long coffeeId){
        //Coffee coffee = new Coffee(0L, "바닐라 라떼", "Vanilla Latte", 6000);
        //return coffee;

        return findVerifiedCoffeeByQuery(coffeeId);
    }

    public Page<Coffee> findCoffees(int page, int size){
        /*List<Coffee> coffees = List.of(
                new Coffee(0L, "바닐라 라떼", "Vanilla Latte", 6000),
                new Coffee(1L, "카페 라떼", "Caffee Latte", 5000)
        );
        return coffees;*/
        return coffeeRepository.findAll(PageRequest.of(page, size,
                Sort.by("coffeeId").descending()));
    }

    public void deleteCoffee(long coffeeId){
        Coffee coffee = findVerifiedCoffee(coffeeId);
        coffeeRepository.delete(coffee);
    }

    private void verifyExistCoffee(String coffeeCode) {
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if(coffee.isPresent())
            throw new BusinessLogicException(ExceptionCode.COFFEE_CODE_EXISTS);
    }

    private Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        Coffee findCoffee =
                optionalCoffee.orElseThrow(() ->
        new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));

        return findCoffee;
    }

    private Coffee findVerifiedCoffeeByQuery(long coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findByCoffee(coffeeId);
        Coffee findCoffee =
                optionalCoffee.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));

        return findCoffee;
    }
}
