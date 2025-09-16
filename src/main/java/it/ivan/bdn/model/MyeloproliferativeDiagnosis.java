package it.ivan.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "myeloproliferative_diagnosis", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class MyeloproliferativeDiagnosis
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "medical_record_id", referencedColumnName = "id", nullable = false)
    private MedicalRecord medicalRecord;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "who2016_id", referencedColumnName = "id")
    private Who2016 who2016;

    @Basic
    @Column(name = "biopsy")
    private String biopsy;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "bone_marrow_fibrosis_id", referencedColumnName = "id")
    private BoneMarrowFibrosis boneMarrowFibrosis;

    @Basic
    @Column(name = "genetic_mutation")
    private String geneticMutation;

    @Basic
    @Column(name = "mutation_type")
    private String mutationType;

    @Basic
    @Column(name = "splenomegaly")
    private String splenomegaly;

    @Basic
    @Column(name = "spleen_length")
    private Double spleenLength;

    @Basic
    @Column(name = "spleen_diameter")
    private Double spleenDiameter;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "symptom_id", referencedColumnName = "id")
    private Symptom symptom;

    @Basic
    @Column(name = "previous_thrombotic_episode")
    private String previousThromboticEpisode;

    @Basic
    @Column(name = "thrombotic_episode_site")
    private String thromboticEpisodeSite;

    @Basic
    @Column(name = "pv_risk")
    private String pvRisk;

    @Basic
    @Column(name = "et_risk")
    private String etRisk;

    @Basic
    @Column(name = "ipss_risk")
    private String ipssRisk;

    @Transient
    private String hospitalCode;
}
