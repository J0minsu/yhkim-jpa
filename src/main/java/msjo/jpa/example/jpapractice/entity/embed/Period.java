package msjo.jpa.example.jpapractice.entity.embed;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
//@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Period {

    //기간
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Boolean isWork() {
        return Boolean.TRUE;
    }

}
