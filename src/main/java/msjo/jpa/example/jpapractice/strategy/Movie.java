package msjo.jpa.example.jpapractice.strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Movie extends StrategyItem {

    private String director;
    private String actor;

}
