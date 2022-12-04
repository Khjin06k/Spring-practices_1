package com.example.entity_mapping.may_to_one_bidirection;

import com.example.Member.entity.Member;
import com.example.Order.entity.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaManyToOneBiDirectionConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            mappingManyToOneBiDirection();
        };
    }

    private void mappingManyToOneBiDirection() {
        tx.begin();
        Member member = new Member("kjava@gmail.com", "김자바", "010-1111-1111");
        Order order = new Order();

        member.addOrder(order); //member 객체에 order 객체 추가
        //추가하지 않을 경우 member 객체로 order 객체 조회가 불가능 (1차 캐시에 없기 때문)

        order.addMember(member); //order 객체에 member 객체 추가
        //추가하지 않을 경우 MEMBER_ID 필드가 null이 되어 참조할 객체가 없게 됨

        em.persist(member);
        em.persist(order);

        tx.commit();

        Member findMember = em.find(Member.class, 1L); //회원 정보를 1차 캐시에서 조회

        findMember
                .getOrders()
                .stream()
                .forEach(findOrder -> {
                    System.out.println("findOrder : " +
                            findOrder.getOrderId() + ", " +
                            findOrder.getOrderStatus());
                });
    }
}
