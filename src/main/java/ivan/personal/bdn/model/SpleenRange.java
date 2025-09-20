package ivan.personal.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "spleen_range", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class SpleenRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "value")
    private String value;
}
