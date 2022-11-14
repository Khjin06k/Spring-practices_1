package com.example.Member.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/members", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
    //회원 등록
    @PostMapping
    public String postMember(@RequestParam("email") String email,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone) {
        System.out.println("# email : " + email);
        System.out.println("# name : " + name);
        System.out.println("# phone : " + phone);

        String response = "{\"" + "email\" : \"" + email + "\"," +
                "\"name\" : " + name + "\", \"" +
                "\"phone\" : \"" + phone + "\"}";

        return response;
    }

    //회원 조회
    @GetMapping("/{member-id}")
    public String getMember(@PathVariable("member-id") long memberId){
        System.out.println("# memberId : " + memberId);

        return null;
    }

    @GetMapping
    public String getMembers(){
        System.out.println("# get Members");

        return null;
    }
}
