package hello.hello_spring.controller;


import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //여기서 이 Controller 어노테이션이 있으면 최초 서버가 실행될때 스프링 컨테이너에
    //해당 클래스 객체를 생성하고 컨테이너에 해당 객체를 담아두게 된다.
    //이를 스프링 컨테이너에서 스프링 빈이 관리된다 말하는거다.
    //private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    //스프링 컨테이너에서 이 생성자를 자동으로 가져온다.
    //이렇게 스프링을 통해 주입을 하는것을 DI Dependency Injection 이라 한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //postMapping 시 여기로온다
    //매개변수에 있는 객체안 변수값에 name에 값이 매핑되서 들어간다.
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
