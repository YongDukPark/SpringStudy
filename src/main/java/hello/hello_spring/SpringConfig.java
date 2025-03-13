package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //JDBC를 사용할경우 필요했던 영역이다.
//    private DataSource dataSource;
//
//    //여기에서 스프링이 자동으로 데이터소스 객체를 가져올수 있도록 생성자 주입을 해준다.
//    //자체적으로 스프링이 빈을 주입하도록 해준다.
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    //원래대로 스펙에선 아래와같이 받아줘야한다. 그러나 스프링에서 알아서 DI해주기때문에 제외한다.
    //@PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
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
//        return new JdbcTemplateMemberRepository(dataSource);

        return new JpaMemberRepository(em);

    }

}
