package hello.hello_spring.domain;


import jakarta.persistence.*;

//이 어노테이션을 적을경우
//JAP가 관리하는 엔티티라는 뜻이다.
@Entity
public class Member {

    //Id Pk를 설정하는 어노테이션이다.
    //GenneratedValue 자동 증가 Sequnce를 지정하는 어노테이션이다.
    //이를 IDENTITY 전략이라 한다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //이와같이 변수와 칼럼명을 매핑시킬수 있다.
    //이렇게 하면 칼럼명이 name인 칼럼에 매핑이된다.
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
