package by.sakhonchik.hestask.validation;

import by.sakhonchik.hestask.repositories.UserAccountRepository;
import by.sakhonchik.hestask.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {


    private UserAccountService userAccountService;


    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Autowired
    public void setUserAccountRepository(UserAccountRepository userAccountRepository) {

    }


    public void initialize(UniqueUsername uniqueUsername) {
    }

    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        return !userAccountService.isUsernameExist(username);
    }
}

