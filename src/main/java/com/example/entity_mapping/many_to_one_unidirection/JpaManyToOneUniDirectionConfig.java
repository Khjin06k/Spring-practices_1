package com.example.entity_mapping.many_to_one_unidirection;

import com.example.Member.entity.Member;
import com.example.Order.entity.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaManyToOneUniDirectionConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            mappingManyToOneUniDirection();
        };
    }

    private void mappingManyToOneUniDirection() {
        tx.begin();
        Member member = new Member("kjava@gmail.com", "김자바", "010-1111-1111");

        em.persist(member); //회원 정보 저장

        Order order = new Order();
        order.addMember(member); //외래키 역할
        em.persist(order);

        tx.commit();

        Order findOrder = em.find(Order.class, 1L);
        System.out.println("findOrder : " + findOrder.getMember().getMemberId() +
                "," + findOrder.getMember().getEmail());
    }
}
