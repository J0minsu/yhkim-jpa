package msjo.jpa.example.jpapractice.repository;

import msjo.jpa.example.jpapractice.domain.dto.request.MemberSearchResponse;
import msjo.jpa.example.jpapractice.domain.entity.Member;
import msjo.jpa.example.jpapractice.domain.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : msjo.jpa.example.jpapractice.repository
 * fileName       : MemberRepositoryTest
 * author         : ms.jo
 * date           : 2023/09/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/26        ms.jo       최초 생성
 */
@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private TeamRepository teamRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void 데이터_세팅() {
        Team teamA = Team.of("TeamA");
        Team teamB = Team.of("TeamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member memberA = Member.of("memberA", 18, teamA);
        Member memberB = Member.of("memberB", 28, teamA);
        Member memberC = Member.of("memberC", 38, teamB);
        Member memberD = Member.of("memberD", 48, teamB);

        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberRepository.save(memberC);
        memberRepository.save(memberD);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void 멤버_레포지토리_테스트() {

        System.out.println("memberRepository.getClass() = " + memberRepository.getClass());

        Member member = Member.of("memberA");

        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장
    }

    @Test
    public void 기본_CRUD_검증() {

        Member member1 = Member.of("member1");
        Member member2 = Member.of("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        //단건 조회 검증
        Member findMember1 =
                memberRepository.findById(member1.getId()).get();
        Member findMember2 =
                memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증 memberJpaRepository.delete(member1);
        memberRepository.delete(member2);
        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(1);
    }

    @Test
    public void 네이밍_메소드_테스트() {


        List<Member> members = memberRepository.findByUsernameAndAgeIsGreaterThan("memberA", 10);
        List<Member> memberList = memberRepository.findTop3ByAgeLessThan(50);
        assertThat(members.size()).isEqualTo(1);
        assertThat(memberList.size()).isEqualTo(3);
    }

    @Test
    public void 쿼리_어노테이션_테스트() {

        List<Member> members = memberRepository.findUser("memberA", 10);
        assertThat(members.size()).isEqualTo(1);

    }

    @Test
    public void 컬렉션_파라미터_테스트() {

        List<Member> members = memberRepository.findByNames(List.of("memberA", "memberB", "memberC", "memberD"));
        assertThat(members.size()).isEqualTo(4);
    }

    @Test
    public void DTO_조회_테스트() {

        List<MemberSearchResponse> memberSearchResponses = memberRepository.findMemberWithTeam();
        memberSearchResponses.forEach(System.out::println);
        assertThat(memberSearchResponses.size()).isEqualTo(4);

    }

    @Test
    public void 기본_페이징_테스트() {

        memberRepository.save(Member.of("Member1", 10, null));
        memberRepository.save(Member.of("Member2", 10, null));
        memberRepository.save(Member.of("Member3", 10, null));
        memberRepository.save(Member.of("Member4", 10, null));
        memberRepository.save(Member.of("Member5", 10, null));
        memberRepository.save(Member.of("Member6", 10, null));

        int age = 10;
        int page = 0;
        int limit = 3;

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "username"));
        /*
        Page<Member> members = memberRepository.findByAge(age, pageRequest);
        List<Member> memberContents = members.getContent();

        assertThat(memberContents.size()).isEqualTo(3);
        assertThat(members.getTotalElements()).isEqualTo(6);
        assertThat(members.getTotalPages()).isEqualTo(2);
        assertThat(members.getNumber()).isEqualTo(0);
        assertThat(members.isFirst()).isTrue();
        assertThat(members.hasNext()).isTrue();
        */

        Slice<Member> members = memberRepository.findByAge(age, pageRequest);
        List<Member> memberContents = members.getContent();

        assertThat(memberContents.size()).isEqualTo(3);
//        assertThat(members.getTotalElements()).isEqualTo(6);
//        assertThat(members.getTotalPages()).isEqualTo(2);
        assertThat(members.getNumber()).isEqualTo(0);
        assertThat(members.isFirst()).isTrue();
        assertThat(members.hasNext()).isTrue();
    }

    @Test
    public void 기본_벌크_테스트() {

        memberRepository.save(Member.of("Member1", 10, null));
        memberRepository.save(Member.of("Member2", 15, null));
        memberRepository.save(Member.of("Member3", 20, null));
        memberRepository.save(Member.of("Member4", 25, null));
        memberRepository.save(Member.of("Member5", 30, null));
        memberRepository.save(Member.of("Member6", 35, null));

        int resultCount = memberRepository.bulkAgePlus(20);
        System.out.println("resultCount = " + resultCount);

        memberRepository.findAll().forEach(System.out::println);

    }

    @Test
    public void 지연로딩_테스트() {

//        List<Member> members = memberRepository.findMemberFetchJoin();
        List<Member> members = memberRepository.findByUsernameContaining("Member");
        members.forEach(member -> {
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
        });

    }

    @Test
    public void 쿼리_힌트() {
        Member member = memberRepository.findById(5L).get();

        member.setUsername("ChangeName");

        entityManager.flush();
    }
}