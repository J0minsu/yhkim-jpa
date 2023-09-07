package msjo.jpa.example.jpapractice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import msjo.jpa.example.jpapractice.strategy.BaseEntity;
import org.aspectj.lang.annotation.Before;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String city;

    private String street;

    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
