package by.sakhonchik.hestask.dto;

import by.sakhonchik.hestask.entities.Role;
import by.sakhonchik.hestask.entities.Status;
import lombok.Data;

@Data
public class UserData {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Status status;
}
