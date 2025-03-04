package hello.hello_spring.controller;


import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
