package fr.myriadata.myriainvoice.api.service.invoice;

import fr.myriadata.myriainvoice.api.model.Invoice;
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
public class InvoiceValidationTest {

    private static final Map<String, List<String>> expectedConstraintsByField = new HashMap<>() {{
        put("generate.arg0.number", List.of(
                "{javax.validation.constraints.NotBlank.message}"));
        put("generate.arg0.date", List.of(
                "{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.currency", List.of(
                "{javax.validation.constraints.NotBlank.message}"));
        put("generate.arg0.locale", List.of(
                "{javax.validation.constraints.NotNull.message}",
                "Locale not Allowed"));
        put("generate.arg0.provider", List.of(
                "{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.sender", List.of(
                "{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.recipient", List.of(
                "{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.order", List.of(
                "{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.paymentInstructions", List.of(
                "{javax.validation.constraints.NotNull.message}"));
    }};

    @Inject
    InvoiceService invoiceService;

    @Test
    public void shouldSignalConstraintErrorWhenEmptyInvoice() {
        // GIVEN
        Invoice invoice = new Invoice();

        // WHEN
        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> invoiceService.generate(invoice));

        // THEN
        Assertions.assertNotNull(exception);
        ConstraintViolationAssertions.asserts(expectedConstraintsByField, exception.getConstraintViolations());
    }

    @Test
    public void shouldGenerateInvoice() throws IOException {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();

        // WHEN
        byte[] pdf = invoiceService.generate(invoice);

        // THEN
        Assertions.assertNotNull(pdf);
    }

}
