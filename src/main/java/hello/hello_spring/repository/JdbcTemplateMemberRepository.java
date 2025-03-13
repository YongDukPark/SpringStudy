package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    //만약 아래와같이 생성자가 단하나면 생략이 가능하긴하다.
    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {

        // 밑에 2줄코드로 insert문을 만들어준다.
        // 1. 먼저 DML 종류를 객체로 생성한다.
        // 2. 이후 insert에 필요한 객체에 테이블 네임 및 칼럼 네임을 넣어준다.
        // 이로서 insert의 인터페이스가 만들어진다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        // 밑에 2줄 코드로 넣을 데이터를 가져온다.
        // 이 메소드에 매게변수로 받아온 데이터를 특정 key에 넣어준다
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());


        //여기서 데이터가 실상 insert를 시킨다고 보면된다.
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 파라미터는 (쿼리, RowMapper<Member>, 파라미터) 순으로 전달해야한다.
        List<Member> result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE ID = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 파라미터는 (쿼리, RowMapper<Member>, 파라미터) 순으로 전달해야한다.
        List<Member> result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE NAME = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM MEMBER", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
//        return new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        // 위 코드를 아래와같은 형식으로 람다로 변경이 가능하다.
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
