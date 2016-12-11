package sampoko.vaadinspringdemo.validator;

import sampoko.vaadinspringdemo.model.jpa.hibernate.Applicant;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AgeValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }



    @Test
    public void testTooOld() {

        Applicant applicant = new Applicant();
        applicant.setFirstName("Sponge");
        applicant.setLastName("Bob Square Pants");
        applicant.setDateOfBirth(DateUtils.addYears(new Date(), -101));
        applicant.setMotivation("Need a job.");

        Set<ConstraintViolation<Applicant>> constraintViolations = validator.validate(applicant);
        assertEquals(1, constraintViolations.size());
        assertEquals( "Age must be between 15 and 100 years", constraintViolations.iterator().next().getMessage());

    }

    @Test
    public void testValid() {
        Applicant applicant = new Applicant();
        applicant.setFirstName("Sponge");
        applicant.setLastName("Bob Square Pants");
        applicant.setDateOfBirth(DateUtils.addYears(new Date(), -30));
        applicant.setMotivation("Need a job.");

        Set<ConstraintViolation<Applicant>> constraintViolations = validator.validate(applicant);
        assertEquals(0, constraintViolations.size());
    }



}