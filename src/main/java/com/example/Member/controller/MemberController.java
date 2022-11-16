package com.example.Member.controller;

import com.example.Member.dto.MemberPatchDto;
import com.example.Member.dto.MemberPostDto;
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
import java.util.Map;


@RestController
@RequestMapping(value = "/v1/members")
@Valid
public class MemberController {

    //회원 등록
    @PostMapping
    public ResponseEntity postMember(@RequestBody @Valid MemberPostDto memberPostDto) {

        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-Id") @Positive long memberId,
                                      @RequestBody @Valid MemberPatchDto memberPatchDto){

        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setName("홍길동");

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }

    //회원 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId){
        System.out.println("# memberId : " + memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(){
        System.out.println("# get Members");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId){

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
