package msjo.jpa.example.jpapractice.repository;

import msjo.jpa.example.jpapractice.domain.dto.request.MemberSearchResponse;
import msjo.jpa.example.jpapractice.domain.entity.Member;
import msjo.jpa.example.jpapractice.domain.projections.UsernameOnly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, JpaSpecificationExecutor {

    Optional<Member> findById(Long id);
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

    @Query(value = "select m from Member m",
             countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Query("""
        UPDATE Member m
           SET m.age = m.age + 1 
         WHERE m.age >= :age 
        """)
    int bulkAgePlus(@Param("age") int i);

    @Query("""
        SELECT m
          FROM Member m LEFT JOIN FETCH m.team
    """)
    List<Member> findMemberFetchJoin();
//    Page<Member> findByAge(int age, Pageable pageable);
//    Slice<Member> findByAge(int age, Pageable pageable);


//    List<Member> findByPage(int age, int offset, int limit);

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("SELECT m FROM Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findByUsernameContaining(@Param("username") String username);

    List<UsernameOnly> findProjectionsByUsername(@Param("username") String username);


}
