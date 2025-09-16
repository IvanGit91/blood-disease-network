package it.ivan.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medical_record", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class MedicalRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "medical_record_category_id", referencedColumnName = "id")
    private MedicalRecordCategory medicalRecordCategory;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "medicalRecord")
    private List<FollowUp> followUps = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
