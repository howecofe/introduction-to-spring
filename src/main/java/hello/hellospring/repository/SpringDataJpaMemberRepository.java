package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface가 interface를 상속받을 때는 extends라고 한다.
// interface는 다중 상속이 가능하다.
// 스프링 데이터 JPA가 interface에 대한 구현체를 자동으로 생성하여 스프링 빈에 등록한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL: select m from Member m where m.name = ?
    Optional<Member> findByName(String name);
}
