package msjo.jpa.example.jpapractice.repository;

import msjo.jpa.example.jpapractice.domain.dto.request.MemberSearchResponse;
import msjo.jpa.example.jpapractice.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * packageName    : msjo.jpa.example.jpapractice.repository
 * fileName       : MemberRepository
 * author         : ms.jo
 * date           : 2023/09/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/26        ms.jo       최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeIsGreaterThan(String name, int age);

    List<Member> findTop3ByAgeLessThan(int age);

    @Query("""
        SELECT m
          FROM Member m
         WHERE m.username = :username 
           AND m.age > :age
    """)
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("""
        SELECT new msjo.jpa.example.jpapractice.domain.dto.request.MemberSearchResponse(m.id, m.username, t.name) 
          FROM Member m
            JOIN m.team t
        """)
    List<MemberSearchResponse> findMemberWithTeam();

    @Query("""
        SELECT m
          FROM Member m
         WHERE m.username IN :names
    """)
    List<Member> findByNames(@Param("names") List<String> names);

}
