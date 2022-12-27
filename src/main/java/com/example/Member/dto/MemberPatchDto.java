package com.example.Member.dto;

import com.example.Member.entity.Member;
import com.example.NotSpace;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class MemberPatchDto {
    private long memberId;
    private String email;
    @NotSpace(message = "회원 이름은 공백이 아니어야 합니다")
    private String name;
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다")
    private String phone;
    private Member.MemberStatus memberStatus;

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }


}
