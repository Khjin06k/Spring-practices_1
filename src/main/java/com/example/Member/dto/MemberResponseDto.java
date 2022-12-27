package com.example.Member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponseDto  {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
