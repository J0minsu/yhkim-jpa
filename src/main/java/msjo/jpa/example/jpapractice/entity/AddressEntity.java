package msjo.jpa.example.jpapractice.entity;

import lombok.*;
import msjo.jpa.example.jpapractice.entity.embed.Address;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;

    private Address address;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;*/

    public AddressEntity(String city, String street, String zipcode) {
        this.address = new Address(city, street, zipcode);
    }
}
