package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //JPA는 EntityManager로 모든게 동작을 한다.
    //외부 라이브러리를 추가했으니 여기서 주입을 받는거다
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // 이렇게만하면 끝난거다.. 객체를 영속시켜서 데이터를 넣는다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 조회할 타입과 식별자를 넣어주면 끝난다.
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 여기서 보이는 쿼리가 JPQL이라는 쿼리 언어이다.
        // 객체를 대상으로 보내는거다.
        // 쿼리를 해석해보자면 select해온다 객체를 어디 m을
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }
}
