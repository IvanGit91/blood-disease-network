package it.ivan.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="patient", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name="first_name")
    @Size(max = 80)
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Column(name="birth_date", columnDefinition="TIMESTAMP")
    private LocalDateTime birthDate;

    @NotNull
    @Column(name="gender")
    private String gender;

    @NotNull
    @Column(name="age_at_diagnosis")
    private int ageAtDiagnosis;

    @NotNull
    @Column(name="residence")
    private String residence;

    @Column(name="profession")
    private String profession;

    @NotNull
    @Column(name="origin_center")
    private String originCenter;
}
