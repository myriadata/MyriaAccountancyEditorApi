package fr.myriadata.myriainvoice.api.service.invoice;

import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.model.order.Order;
import fr.myriadata.myriainvoice.api.model.order.OrderLine;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@QuarkusTest
public class InvoiceOrderLineValidationTest {

    private static final Map<String, List<String>> expectedConstraintsByField = new HashMap<>() {{
        put("generate.arg0.order.lines[0].description", List.of("{javax.validation.constraints.NotBlank.message}"));
    }};

    @Inject
    InvoiceService invoiceService;

    @Test
    public void shouldSignalConstraintErrorWhenEmptyOrderLine() {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.setOrder(new Order());
        invoice.getOrder().setLines(new ArrayList<>());
        invoice.getOrder().getLines().add(new OrderLine());

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
        invoice.getOrder().setLines(new ArrayList<>());
        invoice.getOrder().getLines().add(new OrderLine());
        invoice.getOrder().getLines().get(0).setDescription("descriptionLine1");

        // WHEN
        byte[] pdf = invoiceService.generate(invoice);

        // THEN
        Assertions.assertNotNull(pdf);
    }

}
