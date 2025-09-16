package it.ivan.bdn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="hospital", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Hospital 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotNull
	@Column(name="code")
    private String code;
	
    @NotNull
	@Column(name="name")
    private String name;
}
