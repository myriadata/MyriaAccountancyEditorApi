package fr.myriadata.myriainvoice.api.service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidOrderValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrder {
    String message() default "Order must contain description or order line";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
