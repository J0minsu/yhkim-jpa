package msjo.jpa.example.jpapractice.entity;

import lombok.Getter;
import lombok.Setter;
import msjo.jpa.example.jpapractice.strategy.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;
    private int count;
}
