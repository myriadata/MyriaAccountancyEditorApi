package fr.myriadata.myriaaccountancyeditor.api.service.invoice;

import fr.myriadata.myriaaccountancyeditor.api.model.Invoice;
import fr.myriadata.myriaaccountancyeditor.api.model.order.Order;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@QuarkusTest
public class InvoiceOrderValidationTest {

    private static final Map<String, List<String>> expectedConstraintsByField = new HashMap<>() {{
        put("generate.arg0.order", List.of("Order must contain description or order line"));
    }};

    @Inject
    InvoiceService invoiceService;

    @Test
    public void shouldSignalConstraintErrorWhenEmptyOrder() {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.setOrder(new Order());

        // WHEN
        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> invoiceService.generate(invoice));

        // THEN
        Assertions.assertNotNull(exception);
        ConstraintViolationAssertions.asserts(expectedConstraintsByField, exception.getConstraintViolations());
    }

    @Test
    public void shouldGenerateInvoiceWithValidDescriptionOrder() throws IOException {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.setOrder(new Order());
        invoice.getOrder().setDescription("description with accents special caracters &é'(§è!çà)-\\\"");

        // WHEN
        byte[] pdf = invoiceService.generate(invoice);

        // THEN
        Assertions.assertNotNull(pdf);
    }

}
