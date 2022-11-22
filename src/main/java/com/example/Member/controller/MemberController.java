package com.example.Member.controller;

import com.example.Member.dto.MemberPatchDto;
import com.example.Member.dto.MemberPostDto;
import com.example.Member.dto.MemberResponseDto;
import com.example.Member.entity.Member;
import com.example.Member.mapper.MemberMapper;
import com.example.Member.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/v1/members")
@Valid
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;


    //의존성 주입
    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    //회원 등록
    @PostMapping
    public ResponseEntity postMember(@RequestBody @Valid MemberPostDto memberPostDto) {

        Member member = mapper.memberPostDtoToMember(memberPostDto);

        Member response = memberService.createMember(member);

        return new ResponseEntity<>(mapper.memeberToMemberResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-Id") @Positive long memberId,
                                      @RequestBody @Valid MemberPatchDto memberPatchDto){

        Member member = mapper.memberPatchDtoToMember(memberPatchDto);

        Member response = memberService.updateMember(member);

        return new ResponseEntity<>(mapper.memeberToMemberResponseDto(response), HttpStatus.OK);
    }

    //회원 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId){
        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memeberToMemberResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(){
        List<Member> members = memberService.findMembers();

        List<MemberResponseDto> response =
                members.stream()
                        .map(member -> mapper.memeberToMemberResponseDto(member))
                        .collect(Collectors.toList());


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId){
        System.out.println("# delete member");

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
