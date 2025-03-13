package hello.hello_spring;

import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.JdbcTemplateMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    //여기에서 스프링이 자동으로 데이터소스 객체를 가져올수 있도록 생성자 주입을 해준다.
    //자체적으로 스프링이 빈을 주입하도록 해준다.
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();

        //여기서 기존 Memory 방식에서 JDBC 방식으로 변경을 진행한다.
//        return new JdbcMemberRepository(dataSource);

        //여기서 JDBC Template을 사용하여 데이터를 가져오는 방식으로 변경한다.
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
