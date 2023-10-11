package msjo.jpa.example.jpapractice.domain.projections;

import org.springframework.beans.factory.annotation.Value;

/**
 * packageName    : msjo.jpa.example.jpapractice.domain.projections
 * fileName       : UsernameOnly
 * author         : ms.jo
 * date           : 2023/10/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/10/11        ms.jo       최초 생성
 */
public interface UsernameOnly {
    @Value("#{target.username + ' ' +  target.age}")
    String getUsername();

}
