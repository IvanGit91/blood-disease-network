package ivan.personal.bdn.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", schema = "bdn_db")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "hospital_code")
    private String hospitalCode;

    @Basic
    @Column(name = "role")
    private String role;

    @Transient
    private String hospitalName;

    @Transient
    private String newPassword;
}
