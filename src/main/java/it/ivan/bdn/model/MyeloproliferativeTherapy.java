package it.ivan.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "myeloproliferative_therapy", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class MyeloproliferativeTherapy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "medical_record_id", referencedColumnName = "id", nullable = false)
    private MedicalRecord medicalRecord;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "therapy_line_id", referencedColumnName = "id")
    private TherapyLine therapyLine;

    @Basic
    @Column(name = "antiplatelet")
    private String antiplatelet;

    @Basic
    @Column(name = "antiplatelet_type")
    private String antiplateletType;

    @Basic
    @Column(name = "support")
    private String support;

    @Basic
    @Column(name = "support_type")
    private String supportType;

    @Basic
    @Column(name = "hematologic")
    private String hematologic;

    @Basic
    @Column(name = "first_line")
    private String firstLine;

    @Basic
    @Column(name = "thrombotic_episode")
    private String thromboticEpisode;

    @Basic
    @Column(name = "episode_site")
    private String episodeSite;

    @Basic
    @Column(name = "adverse_event")
    private String adverseEvent;

    @Basic
    @Column(name = "event_type")
    private String eventType;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "treatment_response_id", referencedColumnName = "id")
    private TreatmentResponse treatmentResponse;

    @Basic
    @Column(name = "ongoing_treatment")
    private String ongoingTreatment;

    @Basic
    @Column(name = "interruption_reason")
    private String interruptionReason;

    @Basic
    @Column(name = "disease_progression")
    private String diseaseProgression;

    @Basic
    @Column(name = "progression_towards")
    private String progressionTowards;
}