package msjo.jpa.example.jpapractice.entity.embed;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    //주소
    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    public String fullAddress() {
        return city + " " + street + " , " + zipcode;
    }

}
