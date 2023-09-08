package msjo.jpa.example.jpapractice.cascade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * packageName    : msjo.jpa.example.jpapractice.cascade
 * fileName       : Child
 * author         : ms.jo
 * date           : 2023/09/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/08        ms.jo       최초 생성
 */
@Entity
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
