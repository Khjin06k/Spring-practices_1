package com.example.entity_mapping.single_mapping.id.identity;

import com.example.Member.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class JpaIdIdentityMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            tx.begin();
            em.persist(new Member()); //기본키 memberId를 적용하지 않음
            tx.commit();
            Member member = em.find(Member.class, 1L); //조회가 가능함

            System.out.println("# memberId: " + member.getMemberId());

        };
    }
}
