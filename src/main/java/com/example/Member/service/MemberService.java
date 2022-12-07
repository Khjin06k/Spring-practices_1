package com.example.Member.service;

import com.example.Member.entity.Member;
import com.example.Member.repository.MemberRepository;
import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member){ //postMember
        //Member createdMember = member;
        //return createdMember;
        verifyExistsEmail(member.getEmail());
        return memberRepository.save(member);
    }

    public Member updateMember(Member member){ //patchMember
        //Member updatedMember = member;
        //return updatedMember;
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        findMember.setModifiedAt(LocalDateTime.now());

        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId){ //getMember
        //Member member = new Member(memberId, "kjava@gmail.com", "김자바", "010-1111-1111");
        //throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUNT);
        //return member;
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size){ //getMembers
        /*List<Member> members = List.of(
                new Member(1, "kjava@gmail.com", "김자바", "010-1111-1111"),
                new Member(2, "ljava@gmail.com", "이자바", "010-2222-2222")
       );*/
        //return members;
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId){ //deleteMember
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
