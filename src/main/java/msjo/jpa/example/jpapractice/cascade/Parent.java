package msjo.jpa.example.jpapractice.cascade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : msjo.jpa.example.jpapractice.cascade
 * fileName       : Parent
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
@ToString
public class Parent {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}
