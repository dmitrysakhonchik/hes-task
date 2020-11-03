package by.sakhonchik.hestask.dto;

import by.sakhonchik.hestask.entities.Role;
import by.sakhonchik.hestask.entities.Status;
import by.sakhonchik.hestask.validation.*;
import lombok.Data;

@Data
public class UserAccountDto {

    private Long id;

    @UniqueUsername
    @ValidUsername
    private String username;

    @ValidPassword
    private String password;

    @ValidName
    private String firstName;

    @ValidName
    private String lastName;

    private Role role;

    private Status status;

}
