package by.sakhonchik.hestask.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class UsernameConstraintValidator implements ConstraintValidator<ValidUsername, String> {

    @Override
    public void initialize(ValidUsername arg0) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(

                new LengthRule(3, 16),

                new AllowedRegexRule("\\p{IsLatin}+")

        ));
        RuleResult result = validator.validate(new PasswordData(username));
        return result.isValid();
    }
}

