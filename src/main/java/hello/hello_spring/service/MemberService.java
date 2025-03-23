package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//스프링이 올라올때 이 service가 있으면  이를 스프링 컨테이너에 등록한다.
//원래대로라면 Component로 해야하는데 이게 되는 이유는
//Service 안에 Component 어노테이션이 있기 때무이다.
//@Service

//모든 데이터 변경이 트랜잭션 안에서만 설정이 되어야한다.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    * */
    public Long join(Member member){
//        //같은 이름이 있는 중복 회원x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // 값이 있으면 ifPresent로 가능하다.
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });



        //상단에 코드를 리펙토링
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
