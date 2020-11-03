package by.sakhonchik.hestask.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class NameConstraintValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public void initialize(ValidName arg0) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(

                new LengthRule(1, 16),

                new AllowedRegexRule("\\p{IsLatin}+")


        ));
        RuleResult result = validator.validate(new PasswordData(name));
        return result.isValid();
    }

}
