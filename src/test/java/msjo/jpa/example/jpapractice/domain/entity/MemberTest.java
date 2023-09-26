package msjo.jpa.example.jpapractice.domain.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : msjo.jpa.example.jpapractice.domain.entity
 * fileName       : MemberTest
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
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void 엔티티_테스트() {
        Team teamA = Team.of("TeamA");
        Team teamB = Team.of("TeamB");

        em.persist(teamA);
        em.persist(teamB);

        Member memberA = Member.of("memberA", 18, teamA);
        Member memberB = Member.of("memberB", 28, teamA);
        Member memberC = Member.of("memberC", 38, teamB);
        Member memberD = Member.of("memberD", 48, teamB);

        em.persist(memberA);
        em.persist(memberB);
        em.persist(memberC);
        em.persist(memberD);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("""
                SELECT m
                  FROM Member m
                """, Member.class).getResultList();

        members.forEach(member -> {
            System.out.println("member = " + member);
            System.out.println("member.team = " + member.getTeam());
        });
    }

}