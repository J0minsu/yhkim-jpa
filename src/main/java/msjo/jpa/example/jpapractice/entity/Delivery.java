package msjo.jpa.example.jpapractice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import msjo.jpa.example.jpapractice.constants.DeliveryStatus;
import msjo.jpa.example.jpapractice.strategy.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
