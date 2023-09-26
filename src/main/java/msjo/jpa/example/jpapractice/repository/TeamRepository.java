package msjo.jpa.example.jpapractice.repository;

import msjo.jpa.example.jpapractice.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : msjo.jpa.example.jpapractice.repository
 * fileName       : TeamRepository
 * author         : ms.jo
 * date           : 2023/09/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/26        ms.jo       최초 생성
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}
