package msjo.jpa.example.jpapractice.repository;

import lombok.RequiredArgsConstructor;
import msjo.jpa.example.jpapractice.domain.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * packageName    : msjo.jpa.example.jpapractice.repository
 * fileName       : MemberRepositoryImpl
 * author         : ms.jo
 * date           : 2023/10/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/10/10        ms.jo       최초 생성
 */

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final EntityManager em;


    @Override
    public List<Member> findMemberCustom() {

        return  em.createQuery("""
                SELECT m
                  FROM Member m
                """, Member.class).getResultList();
    }
}
