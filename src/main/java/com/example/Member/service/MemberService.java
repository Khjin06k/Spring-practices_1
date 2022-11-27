package com.example.Member.service;

import com.example.Member.entity.Member;
import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Member createMember(Member member){ //postMember
        Member createdMember = member;
        return createdMember;
    }

    public Member updateMember(Member member){ //patchMember
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId){ //getMember
        Member member = new Member(memberId, "kjava@gmail.com", "김자바", "010-1111-1111");
        //throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUNT);
        return member;
    }

    public List<Member> findMembers(){ //getMembers
        List<Member> members = List.of(
                new Member(1, "kjava@gmail.com", "김자바", "010-1111-1111"),
                new Member(2, "ljava@gmail.com", "이자바", "010-2222-2222")
        );
        return members;
    }

    public void deleteMember(long memberId){ //deleteMember

    }
}
