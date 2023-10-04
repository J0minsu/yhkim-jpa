package msjo.jpa.example.jpapractice.repository;

import msjo.jpa.example.jpapractice.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : msjo.jpa.example.jpapractice.repository
 * fileName       : MemberJpaRepositoryTest
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
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void 멤버_레포지토리_테스트() {

        Member member = Member.of("memberA");

        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성 보장
    }

    @Test
    public void 기본_CRUD_검증() {

        Member member1 = Member.of("member1");
        Member member2 = Member.of("member2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        //단건 조회 검증
        Member findMember1 =
                memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 =
                memberJpaRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        //리스트 조회 검증
        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증 memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);
        long deletedCount = memberJpaRepository.count();
        assertThat(deletedCount).isEqualTo(1);
    }

    @Test
    public void 기본_페이징_테스트() {

        memberJpaRepository.save(Member.of("Member1", 10, null));
        memberJpaRepository.save(Member.of("Member2", 10, null));
        memberJpaRepository.save(Member.of("Member3", 10, null));
        memberJpaRepository.save(Member.of("Member4", 10, null));
        memberJpaRepository.save(Member.of("Member5", 10, null));
        memberJpaRepository.save(Member.of("Member6", 10, null));

        int age = 10;
        int offset = 2;
        int limit = 3;

        List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
        long totalCount = memberJpaRepository.totalCount(age);

        assertThat(members.size()).isEqualTo(limit);
        assertThat(totalCount).isEqualTo(6);
    }

    @Test
    public void 기본_벌크_테스트() {

        memberJpaRepository.save(Member.of("Member1", 10, null));
        memberJpaRepository.save(Member.of("Member2", 15, null));
        memberJpaRepository.save(Member.of("Member3", 20, null));
        memberJpaRepository.save(Member.of("Member4", 25, null));
        memberJpaRepository.save(Member.of("Member5", 30, null));
        memberJpaRepository.save(Member.of("Member6", 35, null));

        memberJpaRepository.bulkAgePlus(20);

        memberJpaRepository.findAll().forEach(System.out::println);

    }
}