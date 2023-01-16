package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private MemberService memberService;

    //DI - 1. 필드 주입
    //@Autowired private MemberService memberService;

    //DI - 2. setter 주입
    //@Autowired
    //public MemberService setMemberService(MemberService memberService) {
    //    this.memberService = memberService;
    //}

    //DI - 3. 생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
