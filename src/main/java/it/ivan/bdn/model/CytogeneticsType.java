package it.ivan.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cytogenetics_type", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CytogeneticsType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "description")
    private String description;
}
