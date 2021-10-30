package fr.myriadata.myriaaccountancyeditor.api.service.invoice;

import fr.myriadata.myriaaccountancyeditor.api.model.Invoice;
import fr.myriadata.myriaaccountancyeditor.api.model.common.Address;
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
public class InvoiceProviderAddressValidationTest {

    private static final Map<String, List<String>> expectedConstraintsByField = new HashMap<>() {{
        put("generate.arg0.provider.address.identification", List.of("{javax.validation.constraints.NotBlank.message}"));
    }};

    @Inject
    InvoiceService invoiceService;

    @Test
    public void shouldSignalConstraintErrorWhenEmptyAddressProvider() {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.getProvider().setAddress(new Address());

        // WHEN
        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> invoiceService.generate(invoice));

        // THEN
        Assertions.assertNotNull(exception);
        ConstraintViolationAssertions.asserts(expectedConstraintsByField, exception.getConstraintViolations());
    }

    @Test
    public void shouldGenerateInvoiceWithValidProviderAddress() throws IOException {
        // GIVEN
        Invoice invoice = InvoiceTestFactory.createLightInvoice();
        invoice.getProvider().setAddress(new Address());
        invoice.getProvider().getAddress().setIdentification("identificationAddressHeadOffice");

        // WHEN
        byte[] pdf = invoiceService.generate(invoice);

        // THEN
        Assertions.assertNotNull(pdf);
    }
}
