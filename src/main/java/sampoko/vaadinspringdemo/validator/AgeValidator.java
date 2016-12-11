package sampoko.vaadinspringdemo.validator;

import org.apache.commons.lang3.time.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class AgeValidator implements ConstraintValidator<Age, Date> {

    private Age age;

    @Override
    public void initialize(Age age) {
        this.age = age;
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        Date current = new Date();

        if (date.before(DateUtils.addYears(current, age.max() * -1))) {
            return false;
        }
        if (date.after(DateUtils.addYears(current, age.min() * -1))) {
            return false;
        }

        return true;
    }
}
