package msjo.jpa.example.jpapractice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import msjo.jpa.example.jpapractice.entity.embed.Address;
import msjo.jpa.example.jpapractice.entity.embed.Period;
import msjo.jpa.example.jpapractice.strategy.BaseEntity;
import org.aspectj.lang.annotation.Before;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"addressHistory", "orders"})
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Period period;

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "office_city")),
            @AttributeOverride(name = "street", column = @Column(name = "office_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "office_zipcode"))
    })
    private Address officeAddress;


    @ElementCollection
    @CollectionTable(name = "favorites",
                     joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "food_name")
    private Set<String> favorites = new HashSet<>();

    /*@ElementCollection
    @CollectionTable(name = "address",
                     joinColumns = @JoinColumn(name = "member_id"))
    private List<Address> addressHistory = new ArrayList<>();*/

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
