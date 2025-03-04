package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //기본적으로 넘기는 방식
    @GetMapping("hello")
    public String hello(Model model){
        // 여기서 data 달러브레이스에 있는 값이
        // hello 로 들어가게 된다.
        model.addAttribute("data", "hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model ){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   //http response 바디영역에 데이터를 직접 넣겠따는 뜻임 그리고 문자 자체를 return하게됨
    public String helloString(@RequestParam("name") String name){
        return "Hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }


    static class Hello {

        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
