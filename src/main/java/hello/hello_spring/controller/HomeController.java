package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //여기를 최초로 오게된다 왜냐하면 정적페이지 static패키지에 있는 index.html 보다
    //컨트롤러 우선순위가 더 높기 때문이다.
    //1순위 컨트롤러 > 2순위 static index
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
