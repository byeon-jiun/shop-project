package com.shop.service;

import com.shop.dto.MemberFromDto;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFromDto memberFromDto = new MemberFromDto();
        memberFromDto.setEmail("test@email.com");
        memberFromDto.setName("변지운");
        memberFromDto.setAddress("서울시 강남구 테헤란로");
        memberFromDto.setPassword("1234");
        return Member.createMember(memberFromDto,passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getAddress(), savedMember.getAddress());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getRole(), savedMember.getRole());

    }

    @Test
    @DisplayName("중복회원가입 테스트")
    public void  saveDuplicateMemberTest(){
        Member member = createMember();
        Member member1 = createMember();
        memberService.saveMember(member);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member1);
        });
        assertEquals("이미 가입된 회원 입니다.", e.getMessage());
    }
}