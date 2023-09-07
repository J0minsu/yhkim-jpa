package msjo.jpa.example.jpapractice.strategy;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {

    private String createBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    @PrePersist
    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void setLastModifiedDate() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}
