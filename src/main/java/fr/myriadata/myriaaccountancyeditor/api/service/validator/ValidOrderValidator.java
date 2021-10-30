package fr.myriadata.myriaaccountancyeditor.api.service.validator;

import fr.myriadata.myriaaccountancyeditor.api.model.order.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@ApplicationScoped
public class ValidOrderValidator implements ConstraintValidator<ValidOrder, Order> {

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(order) && (Objects.nonNull(order.getDescription()) ||
               (Objects.nonNull(order.getLines()) && !order.getLines().isEmpty()));
    }

}
