package msjo.jpa.example.jpapractice.entity;

import lombok.Getter;
import lombok.Setter;
import msjo.jpa.example.jpapractice.strategy.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Item extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
}
