package sampoko.vaadinspringdemo.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target( value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {

    int min() default 15;

    int max() default 63;

    String message() default "{Age}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
