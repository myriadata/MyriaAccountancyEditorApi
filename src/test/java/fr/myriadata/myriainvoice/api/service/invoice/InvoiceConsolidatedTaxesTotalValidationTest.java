package fr.myriadata.myriainvoice.api.service.invoice;

import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.model.tax.ConsolidatedTaxes;
import fr.myriadata.myriainvoice.api.model.tax.ValueAddedTax;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@QuarkusTest
public class InvoiceConsolidatedTaxesTotalValidationTest {

    private static final Map<String, List<String>> expectedConstraintsByField = new HashMap<>() {{
        put("generate.arg0.consolidatedTaxes.byAmount[1].taxAmount",          List.of("{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.consolidatedTaxes.byAmount[1].baseAmount",         List.of("{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.consolidatedTaxes.byAmount[1].includingTaxAmount", List.of("{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.consolidatedTaxes.total.taxAmount",                List.of("{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.consolidatedTaxes.total.baseAmount",               List.of("{javax.validation.constraints.NotNull.message}"));
        put("generate.arg0.consolidatedTaxes.total.includingTaxAmount",       List.of("{javax.validation.constraints.NotNull.message}"));
    }};

    @Inject
    InvoiceService invoiceService;

    @Test
    public void shouldSignalConstraintErrorWhenEmptyValueAddedTax() {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.setConsolidatedTaxes(new ConsolidatedTaxes());
        invoice.getConsolidatedTaxes().setByAmount(new HashMap<>());
        invoice.getConsolidatedTaxes().getByAmount().put(BigDecimal.ONE, new ValueAddedTax());
        invoice.getConsolidatedTaxes().setTotal(new ValueAddedTax());

        // WHEN
        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> invoiceService.generate(invoice));

        // THEN
        Assertions.assertNotNull(exception);
        ConstraintViolationAssertions.asserts(expectedConstraintsByField, exception.getConstraintViolations());
    }

    @Test
    public void shouldGenerateInvoiceWithValidValueAddedTaxes() throws IOException {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.setConsolidatedTaxes(new ConsolidatedTaxes());
        invoice.getConsolidatedTaxes().setByAmount(new HashMap<>());
        invoice.getConsolidatedTaxes().getByAmount().put(BigDecimal.ONE, new ValueAddedTax());
        invoice.getConsolidatedTaxes().getByAmount().get(BigDecimal.ONE).setBaseAmount(BigDecimal.valueOf(1370.42));
        invoice.getConsolidatedTaxes().getByAmount().get(BigDecimal.ONE).setTaxAmount(BigDecimal.valueOf(0));
        invoice.getConsolidatedTaxes().getByAmount().get(BigDecimal.ONE).setIncludingTaxAmount(BigDecimal.valueOf(1370.42));
        invoice.getConsolidatedTaxes().setTotal(new ValueAddedTax());
        invoice.getConsolidatedTaxes().getTotal().setBaseAmount(BigDecimal.valueOf(1370.42));
        invoice.getConsolidatedTaxes().getTotal().setTaxAmount(BigDecimal.valueOf(0));
        invoice.getConsolidatedTaxes().getTotal().setIncludingTaxAmount(BigDecimal.valueOf(1370.42));

        // WHEN
        byte[] pdf = invoiceService.generate(invoice);

        // THEN
        Assertions.assertNotNull(pdf);
    }

}
