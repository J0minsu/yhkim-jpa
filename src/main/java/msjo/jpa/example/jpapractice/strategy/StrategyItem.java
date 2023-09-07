package msjo.jpa.example.jpapractice.strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
@Getter
@Setter
@ToString
public abstract class StrategyItem {

    @Id @GeneratedValue
    @Column(name = "strategy_item_id")
    private Long id;

    private String name;
    private int price;
}
