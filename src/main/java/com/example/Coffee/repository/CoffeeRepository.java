package com.example.Coffee.repository;

import com.example.Coffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    @Query(value = "SELECT c FROM Coffee c WHERE c.coffeeId = :coffeeId")
    //SQL 문이 아닌 JPQL문 >> coffeeId를 이용해 커피 정보를 조회
    // COFFEE 테이블이 아닌 Coffee 객체를 지정 / coffee_id 칼럼이 아닌 coffeeId 필드를 지정
    // Coffee 클래스의 별칭인 c를 이용하여 (* 사용이 아님) 모든 필드를 조회함
    Optional<Coffee> findByCoffee(long coffeeId);
}
