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

    /**
     * Child 의 관리포인트가 Parent 일 때만 cascade 설정에 유리
     * 등록의 Life Cycle 이 거의 동일할 때
     * 단독 소유자일 때
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}
