package com.example.Member.mapper;


import com.example.Member.dto.MemberPatchDto;
import com.example.Member.dto.MemberPostDto;
import com.example.Member.dto.MemberResponseDto;
import com.example.Member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memeberToMemberResponseDto(Member member);
}
