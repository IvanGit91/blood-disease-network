package it.ivan.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "follow_up", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class FollowUp
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "medical_record_id", referencedColumnName = "id", nullable = false)
    private MedicalRecord medicalRecord;

    @Basic
    @Column(name = "date")
    private Timestamp date;

    @Basic
    @Column(name = "exitus")
    private String exitus;

    @Basic
    @Column(name = "cause")
    private String cause;

    @Basic
    @Column(name = "num_cycles")
    private Integer numCycles;

    @Basic
    @Column(name = "pathology_status")
    private String pathologyStatus;
}
