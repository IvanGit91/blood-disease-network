package ivan.personal.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lymphoma_therapy", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class LymphomaTherapy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "hospital_id", referencedColumnName = "id", nullable = false)
    private Hospital hospital;
}
