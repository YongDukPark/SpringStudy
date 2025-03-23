package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//T는 엔티티가 들어가고 두번째는 PK가 들어간다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //이렇게만하면 Spring Jpa는 끝이난다.
    //이는 JpaRepository 인터페이스에서 공통적으로 구현된 메서드들을 가져와 사용하는 것이다.
    //그러나 아래와같이 PK기반이 아닌 별도의 것들은
    //아래와 같이 구현을 진행하면 아래와 같은 쿼리가 생성이 된다.
    //JPQL < select m from Member m where m.name = ?
    //여기서 중점적으로 봐야하는 사항은 인터페이스 이름만으로도 개발이 끝난다는 것이다.
    //이는 규칙을 통해서 JPQL이 생성되는거다.
    //예를들면 findByNameAndId 이와같이 메서드 이름을 주고 매게변수를
    //(String name, long id) 이와같이 줄경우 그에맞춰 JPQL이 생성된다.
    //만약 동적 쿼리를 사용할 경우에는 "Qyery DSL" 라이브러리를 사용하면 된다.
    @Override
    Optional<Member> findByName(String name);
}
