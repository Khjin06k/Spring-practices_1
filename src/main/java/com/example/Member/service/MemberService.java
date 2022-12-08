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
    //MemberRepository DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member){ //postMember
        //Member createdMember = member;
        //return createdMember;
        verifyExistsEmail(member.getEmail()); //회원 존재 여부 확인 >> 존재할 경우 Exception 발생
        return memberRepository.save(member); //존재하지 않는 회원일 경우 등록 진행
    }

    public Member updateMember(Member member){ //patchMember
        //Member updatedMember = member;
        //return updatedMember;
        Member findMember = findVerifiedMember(member.getMemberId()); //회원이 존재하는지 여부 확인 >> 존재하지 않을 경우 Exception 발생

        //ofNullable : null 값을 허용함으로써 NullPointException이 발생하지 않고 다음 ifPresent를 호출 가능
        //즉, 모든 정보를 수정하는 것이 아닌 선택적으로 정보를 수정하기 때문에 Null 값이 어디서 발생할 지 모르기에 예외 발생 방지를 위해 ofNullable 사용
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name)); //존재하는 회원이고, null 값이 아니라면 해당 정보를 setter 메서드를 이용하여 수정
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone)); //ifPresent는 값이 존재할 경우 메서드 내 코드가 실행되며, 없을 경우 아무 동작하지 않음
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        findMember.setModifiedAt(LocalDateTime.now()); //수정한 시간 저장

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

        //findAll : 메서드 리턴값이 Iterable<T>
    }

    public void deleteMember(long memberId){ //deleteMember
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
        //현재는 값을 직접 지웠지만, 실무에서는 데이터를 직접 삭제하는 것이 아닌 MemberStatus enum을 활용하여 회원 활동 종료 처리를 할 뿐 데이터를 삭제하지 않음
    }

    //쿼리 메서드 : find + By + WHERE 절 칼럼명 형식
    // 확인하고자 하는 칼럼을 WHERE 절로 지정하여 클래스 테이블에서 조회
    public Member findVerifiedMember(long memberId) { //검증된 회원 정보를 리턴
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                //orElseThrow : Optional 객체가 null이 아니라면 리턴, null 값일 경우 예외를 던짐
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    //등록된 이메일이 존재하는지 검증
    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) //Optional 이기 때문에 isPresent를 사용
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
