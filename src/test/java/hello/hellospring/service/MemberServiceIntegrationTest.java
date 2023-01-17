package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 컨테이너와 DB까지 통합한 Integration Test !!
// Integration Test 시 Annotaions
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional
/** 테스트 케이스에 붙은 @Transactional !!!
 *  각 테스트 실행 시, 트랜잭션을 먼저 실행 후 데이터를 넣고, 테스트 끝나면 항상 롤백. (테스트 시 넣었던 데이터 반영 x)
 *  -> 따라서, 데이터 지우는 코드를 안 넣어도 된다! & DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
 *  Rolled back transaction for test
 */
class MemberServiceIntegrationTest {

    // DI - 필드 주입
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given: 머리! 어떤 데이터가 주어졌는데,
        Member member = new Member();
        member.setName("spring");


        //when: 가슴! 이것을 실행했을 때, (뭐를 검증해?)
        Long saveId = memberService.join(member);

        //then: 배! 결과가 이것이 나와야 해~
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}