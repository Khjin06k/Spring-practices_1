package com.example.Member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
//@AllArgsConstructor //모든 멤버 변수를 파라미터로 갖는 Member 생성자 자동 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 자동 생성
@Entity
public class Member {

    @Id
    @GeneratedValue
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
