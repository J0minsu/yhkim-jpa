package msjo.jpa.example.jpapractice.repository;

import msjo.jpa.example.jpapractice.domain.entity.Member;

import java.util.List;

/**
 * packageName    : msjo.jpa.example.jpapractice.repository
 * fileName       : MemberRepositoryCustom
 * author         : ms.jo
 * date           : 2023/10/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/10/10        ms.jo       최초 생성
 */
public interface MemberRepositoryCustom {

    List<Member> findMemberCustom();

}
