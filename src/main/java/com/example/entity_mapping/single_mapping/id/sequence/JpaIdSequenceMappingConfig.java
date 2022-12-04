package com.example.entity_mapping.single_mapping.id.sequence;

import com.example.Member.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class JpaIdSequenceMappingConfig {
    //SEQUENCE 전략을 사용하도록 지정해 영속성 컨택스트에 저장되기 전에 DB가 시쿼스에서 기본키에 해당하는 값을 제공함
    // 시퀀스의 값은 Member 엔티티의 memberId 필드에 할당
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            tx.begin();
            em.persist(new Member()); //기본키 memberId를 적용하지 않음
            Member member = em.find(Member.class, 1L); //조회가 가능함
            System.out.println("# memberId: " + member.getMemberId());
            tx.commit();
        };
    }
}
