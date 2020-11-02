package by.sakhonchik.hestask.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_account",
        schema = "webapp",
        uniqueConstraints = @UniqueConstraint(columnNames = "username"))

public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;


}
