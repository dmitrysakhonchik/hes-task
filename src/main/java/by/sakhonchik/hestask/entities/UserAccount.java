package by.sakhonchik.hestask.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "user_account", schema = "webapp")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String Username;
    private String Password;
    private String firstName;
    private String lastName;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date" , updatable=false)
    private LocalDate createAt;

}
