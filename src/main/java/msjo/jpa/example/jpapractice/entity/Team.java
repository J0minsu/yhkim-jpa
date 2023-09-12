package msjo.jpa.example.jpapractice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : msjo.jpa.example.jpapractice.entity
 * fileName       : Team
 * author         : ms.jo
 * date           : 2023/09/12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/12        ms.jo       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
