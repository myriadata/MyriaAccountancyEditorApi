package fr.myriadata.myriaaccountancyeditor.api.service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllowedLocaleValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedLocale {
    String message() default "Locale not Allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
