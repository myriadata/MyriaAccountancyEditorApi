package fr.myriadata.myriainvoice.api.validator;

import fr.myriadata.myriainvoice.api.model.order.OrderLine;
import fr.myriadata.myriainvoice.api.service.validator.ValidOrderValidator;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import fr.myriadata.myriainvoice.api.model.order.Order;

import javax.inject.Inject;
import java.util.ArrayList;

@QuarkusTest
public class ValidOrderValidatorTest {

    @Inject
    ValidOrderValidator validator;

    @Test
    public void shouldNotValidateNullOrder() {
        // GIVEN
        Order order = null;

        // THEN
        boolean valid = validator.isValid(order,null);

        // WHEN
        Assertions.assertFalse(valid);
    }

    @Test
    public void shouldNotValidateEmptyOrder() {
        // GIVEN
        Order order = new Order();

        // THEN
        boolean valid = validator.isValid(order,null);

        // WHEN
        Assertions.assertFalse(valid);
    }

    @Test
    public void shouldNotValidateEmptyLines() {
        // GIVEN
        Order order = new Order();
        order.setLines(new ArrayList<>());

        // THEN
        boolean valid = validator.isValid(order,null);

        // WHEN
        Assertions.assertFalse(valid);
    }

    @Test
    public void shouldValidateOrderWithOnlyDescription() {
        // GIVEN
        Order order = new Order();
        order.setDescription("description");

        // THEN
        boolean valid = validator.isValid(order,null);

        // WHEN
        Assertions.assertTrue(valid);
    }

    @Test
    public void shouldValidateOrderWithOnlyLine() {
        // GIVEN
        Order order = new Order();
        order.setLines(new ArrayList<>());
        order.getLines().add(new OrderLine());
        order.getLines().get(0).setDescription("description");

        // THEN
        boolean valid = validator.isValid(order,null);

        // WHEN
        Assertions.assertTrue(valid);
    }

}
