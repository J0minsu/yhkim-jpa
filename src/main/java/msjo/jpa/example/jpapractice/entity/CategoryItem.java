package msjo.jpa.example.jpapractice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import msjo.jpa.example.jpapractice.strategy.BaseEntity;

import javax.persistence.*;

//@Entity
@Getter
@Setter
@ToString
public class CategoryItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
