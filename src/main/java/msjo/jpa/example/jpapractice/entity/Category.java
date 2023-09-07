package msjo.jpa.example.jpapractice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import msjo.jpa.example.jpapractice.strategy.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Category extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
