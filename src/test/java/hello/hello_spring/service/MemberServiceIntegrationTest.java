package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


//스프링 테스트이지만 이같은 통합 테스트보다 실상
//기존 memory 방식을 사용한 단위테스트가 더 좋은 테스트라 칭할수 있다.
@SpringBootTest
//이 트랜잭션얼를 하면 쿼리를 전부 실행을 한 이후에 rollback을 시킨다.
//즉 결과를 저장하지않는다.
//테스트 매서드마다 rollback을 시킨다.
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;





    @Test
    // commit 어노테이션을 사용할경우 끝난이후 commit을 진행한다.
    //@Commit
    void 회원가입() {
        //테스트는 통상적으로
        // (given)뭔가 주어졌을때 (when)무엇으로 실행했을때 (then) 이런 결과가 나와야해
        // 위와같은 형식으로 실행이 되게된다.

        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //thenf
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // 왼쪽에는 발생할 예외 오른쪽에는 람다를 실행했을경우 발생
        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        아래와 같은 방법도 있긴하다.
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }
}
