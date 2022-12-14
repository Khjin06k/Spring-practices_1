package com.example.Member.entity;

import com.example.Order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor //모든 멤버 변수를 파라미터로 갖는 Member 생성자 자동 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 자동 생성
@Entity //JPA 관리 대상 엔티티가 되기 위한 필수 애너테이션
public class Member {

    @Id //기본키 직접 할당 (자동 생성도 존재)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    //nullable : null 값 허용 여부, updatable : 수정 가능 여부, unique : 동일한 값 등록 여부
    private String email;

    @Column(length = 100, nullable = false)
    //length : 문자열 길이 제한
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now(); //시간 및 날짜 매핑 진행
    //LocalDateTime은 @Temporal 애너테이션 생략이 가능하며, TIMESTAP 타입과 매핑됨

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    //name : 클래스 필드명과 다른 이름으로 칼럼 생성 진행
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "member") //일대다 매핑 진행, mappedBy에 들어갈 단어는 외래키이며 다에 해당하는 객체인지 확인
    private List<Order> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;
    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("활동 종료"),
        MEMBER_QUIT("회원 탈퇴");
        @Getter
        private String staus;

        MemberStatus(String staus) {
            this.staus = staus;
        }
    }

    public void setOrder(Order order) {
        orders.add(order);
        if (order.getMember() != this) {
            order.setMember(this);
        }
    }

    public Member(Long memberId){
        this.memberId = memberId;
    }

    public Member(String email){
        this.email = email;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    /*@Transient
    // 테이블 칼럼과 매핑하지 않겠다는 의미로 JPA 가 인식
    // DB에 저장되지 않으며, 조회시 매핑되지 않기 때문에 임시 데이터를 메모리에 사용하기 위해서 사용함
    private String age;*/

}
