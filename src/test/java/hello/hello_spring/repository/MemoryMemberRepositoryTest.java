package hello.hello_spring.repository;


import hello.hello_spring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository respository = new MemoryMemberRepository();

    //테스트가 끝날떄마다 repository를 clear시키는 method
    //AfterEach는 테스트가 끝날때마다 실행시키는 어노테이션이다.
    @AfterEach
    public void afterEach(){
        respository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        respository.save(member);

        Member result = respository.findById(member.getId()).get();

        //이와같이 구분이 가능하다 그러니 이는 예전 방법이므로 현재는 잘 사용하지 않음
        //org.junit.jupiter.api.Assertions.assertEquals(member, result);

        //아래와 같은 방법을 사용
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        respository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        respository.save(member2);

        Member reslut = respository.findByName("spring1").get();

        //Assertions.assertThat(member)7        }
        assertThat(reslut).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        respository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        respository.save(member2);

        List<Member> result = respository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    //순서 보장이 안된다.
    //순서를 보게되면 findAll에서 member1 정보와 member2정보가 저장되는걸 확인이 가능한데
    //그걸 이후 findByName에서도 member정보를 set을 진행한다.
    //그런데 respository.findByName("spring1").get() 코드에서 해당 method에서 set한 정보를 가져올려고 하는데
    //이전 findAll에서 set한 정보가 나오게 되어 객체의 주소값이 다르다 나오게된거다.


}
