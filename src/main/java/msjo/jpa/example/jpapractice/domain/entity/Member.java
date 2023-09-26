package msjo.jpa.example.jpapractice.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 * packageName    : msjo.jpa.example.jpapractice.domain.entity
 * fileName       : Member
 * author         : ms.jo
 * date           : 2023/09/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/26        ms.jo       최초 생성
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    protected Member(String username) {
        this.username = username;
    }

    public static Member of(String username) {
         return new Member(username);
    }

    protected Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public static Member of(String username, int age, Team team) {
        return new Member(username, age, team);
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
