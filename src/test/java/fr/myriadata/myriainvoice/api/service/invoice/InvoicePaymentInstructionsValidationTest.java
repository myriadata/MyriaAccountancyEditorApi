package fr.myriadata.myriainvoice.api.service.invoice;

import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@QuarkusTest
public class InvoicePaymentInstructionsValidationTest {

    private static final Map<String, List<String>> expectedConstraintsByField = new HashMap<>() {{
        put("generate.arg0.paymentInstructions.amount", List.of("{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.paymentInstructions.dueDate", List.of("{javax.validation.constraints.NotNull.message}"));
    }};

    @Inject
    InvoiceService invoiceService;

    @Test
    public void shouldSignalConstraintErrorWhenEmpty() {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.setPaymentInstructions(new PaymentInstructions());

        // WHEN
        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> invoiceService.generate(invoice));

        // THEN
        Assertions.assertNotNull(exception);
        ConstraintViolationAssertions.asserts(expectedConstraintsByField, exception.getConstraintViolations());
    }

    @Test
    public void shouldGenerateInvoiceWithValidPaymentInstructions() throws IOException {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.setPaymentInstructions(new PaymentInstructions());
        invoice.getPaymentInstructions().setAmount(BigDecimal.valueOf(1386.26f));
        invoice.getPaymentInstructions().setDueDate(LocalDate.of(2019, 07, 25));

        // WHEN
        byte[] pdf = invoiceService.generate(invoice);

        // THEN
        Assertions.assertNotNull(pdf);
    }
}
