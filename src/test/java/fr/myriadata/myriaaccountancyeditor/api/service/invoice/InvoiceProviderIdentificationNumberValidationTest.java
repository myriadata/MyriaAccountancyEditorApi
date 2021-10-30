package fr.myriadata.myriaaccountancyeditor.api.service.invoice;

import fr.myriadata.myriaaccountancyeditor.api.model.Invoice;
import fr.myriadata.myriaaccountancyeditor.api.model.party.IdentificationNumber;
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
public class InvoiceProviderIdentificationNumberValidationTest {

    private static final Map<String, List<String>> expectedConstraintsByField = new HashMap<>() {{
        put("generate.arg0.provider.variousIdentificationNumbers[0].label", List.of("{javax.validation.constraints.NotBlank.message}"));
        put("generate.arg0.provider.variousIdentificationNumbers[0].id",    List.of("{javax.validation.constraints.NotBlank.message}"));
    }};

    @Inject
    InvoiceService invoiceService;

    @Test
    public void shouldSignalConstraintErrorWhenEmptyIdentificationNumber() {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.getProvider().setVariousIdentificationNumbers(new ArrayList<>());
        invoice.getProvider().getVariousIdentificationNumbers().add(new IdentificationNumber());

        // WHEN
        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> invoiceService.generate(invoice));


        // THEN
        Assertions.assertNotNull(exception);
        ConstraintViolationAssertions.asserts(expectedConstraintsByField, exception.getConstraintViolations());
    }

    @Test
    public void shouldSignalConstraintErrorWhenEmptyIdentificationNumberList() throws IOException {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.getProvider().setVariousIdentificationNumbers(new ArrayList<>());

        // WHEN
        byte[] pdf = invoiceService.generate(invoice);

        // THEN
        Assertions.assertNotNull(pdf);
    }

    @Test
    public void shouldSignalConstraintErrorWhenFulfilledIdentificationNumber() throws IOException {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.getProvider().setVariousIdentificationNumbers(new ArrayList<>());
        invoice.getProvider().getVariousIdentificationNumbers().add(new IdentificationNumber());
        invoice.getProvider().getVariousIdentificationNumbers().get(0).setLabel("Siren");
        invoice.getProvider().getVariousIdentificationNumbers().get(0).setId("00 204 265");

        // WHEN
        byte[] pdf = invoiceService.generate(invoice);

        // THEN
        Assertions.assertNotNull(pdf);
    }

}
