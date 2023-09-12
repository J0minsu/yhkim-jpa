package msjo.jpa.example.jpapractice.strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
//@DiscriminatorValue("BB")
public class Book extends StrategyItem {

    private String author;
    private String isbn;

}
