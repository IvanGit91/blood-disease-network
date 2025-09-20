package ivan.personal.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hospital", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "name")
    private String name;
}
