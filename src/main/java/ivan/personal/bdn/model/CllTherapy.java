package ivan.personal.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "cll_therapy", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CllTherapy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "medical_record_id", referencedColumnName = "id", nullable = false)
    private MedicalRecord medicalRecord;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "linea_therapy_id", referencedColumnName = "id")
    private TherapyLine therapyLine;

    @Basic
    @Column(name = "start_date")
    private Timestamp startdate;

    @OneToOne
    @JoinColumn(name = "therapy_type_id", referencedColumnName = "id")
    private TherapyType therapyType;

    @Basic
    @Column(name = "adverse_events")
    private String adverseEvents;

    @OneToOne
    @JoinColumn(name = "event_type_id", referencedColumnName = "id")
    private EventType eventType;

    @OneToOne
    @JoinColumn(name = "treatment_response_id", referencedColumnName = "id")
    private TreatmentResponse treatmentResponse;

    @Basic
    @Column(name = "end_date")
    private Timestamp endDate;

    @Basic
    @Column(name = "ritcher")
    private String ritcher;

    @OneToOne
    @JoinColumn(name = "rai_stage", referencedColumnName = "id")
    private RaiStage raiStage;

    @Basic
    @Column(name = "blood_count")
    private String bloodCount;

    @Basic
    @Column(name = "lymphocytes")
    private Double lymphocytes;

    @Basic
    @Column(name = "hemoglobin")
    private Double hemoglobin;

    @Basic
    @Column(name = "platelets")
    private Double platelets;

    @Basic
    @Column(name = "immunophenotype")
    private String immunophenotype;

    @Basic
    @Column(name = "cd319")
    private Double cd319;

    @Basic
    @Column(name = "cd19")
    private Double cd19;

    @Basic
    @Column(name = "cd20")
    private Double cd20;

    @Basic
    @Column(name = "cd22")
    private Double cd22;

    @Basic
    @Column(name = "cd23")
    private Double cd23;

    @Basic
    @Column(name = "k_plus")
    private Double kPlus;

    @Basic
    @Column(name = "l_plus")
    private Double lPlus;

    @Basic
    @Column(name = "laboratory_tests")
    private String laboratoryTests;

    @Basic
    @Column(name = "igg")
    private Double igg;

    @Basic
    @Column(name = "iga")
    private Double iga;

    @Basic
    @Column(name = "igm")
    private Double igm;

    @Basic
    @Column(name = "ldh")
    private Double ldh;

    @Basic
    @Column(name = "b2m")
    private Double b2M;

    @OneToOne
    @JoinColumn(name = "monoclonal_id", referencedColumnName = "id")
    private MonoclonalComponent monoclonal;

    @Basic
    @Column(name = "tcd")
    private String tcd;

    @Basic
    @Column(name = "spleen_ultrasound")
    private String spleenUltrasound;

    @OneToOne
    @JoinColumn(name = "spleen_size_range_id", referencedColumnName = "id")
    private SpleenRange spleenSizeRange;

    @Basic
    @Column(name = "ct_scan")
    private String ctScan;

    @Basic
    @Column(name = "cytogenetics")
    private String cytogenetics;

    @OneToOne
    @JoinColumn(name = "cytogenetics_type_id", referencedColumnName = "id")
    private CytogeneticsType cytogeneticsType;

    @Basic
    @Column(name = "cytogenetics_notes")
    private String cytogeneticsNotes;

    @Basic
    @Column(name = "fish")
    private String fish;

    @Basic
    @Column(name = "fish_result")
    private String fishResult;

    @Basic
    @Column(name = "biopsy")
    private String biopsy;
}
