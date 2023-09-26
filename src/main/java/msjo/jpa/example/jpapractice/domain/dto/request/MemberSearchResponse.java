package msjo.jpa.example.jpapractice.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : msjo.jpa.example.jpapractice.domain.dto.request
 * fileName       : MemberSearchResponse
 * author         : ms.jo
 * date           : 2023/09/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/26        ms.jo       최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSearchResponse {

    private Long id;
    private String username;
    private String teamName;

}
