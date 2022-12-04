package com.example.entity_mapping.single_mapping.column;

import com.example.Member.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class JpaColumnMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            //testEmailNotNull();
            //testEmailUpdatable();
            //testEmailUnique();
        };
    }

    private void testEmailNotNull() { //email 필드에 아무값도 입력하지 않음 >> 에러 발생
        tx.begin();
        em.persist(new Member());
        tx.commit();
    }

    private void testEmailUpdatable(){ // 이미 등록된 email 주소 >> 수정이 불가능
        tx.begin();
        em.persist(new Member("kjava@gmail.com"));
        Member member = em.find(Member.class, 1L);
        member.setEmail("kjava@naver.com");
        tx.commit();
    }

    private void testEmailUnique(){ //이미 등록된 email값 등록 >> 에러 발셍
        tx.begin();
        em.persist(new Member("kjava@gmail.com"));
        em.persist(new Member("kjava@gmail.com"));
        tx.commit();
    }
}
