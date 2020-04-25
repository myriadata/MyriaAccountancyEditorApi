package fr.myriadata.myriainvoice.api.service.invoice;

import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.model.common.Address;
import fr.myriadata.myriainvoice.api.model.common.Contact;
import fr.myriadata.myriainvoice.api.model.order.Order;
import fr.myriadata.myriainvoice.api.model.party.Provider;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class InvoiceTestFactory {

    private InvoiceTestFactory() {}

    public static Invoice createLightInvoice() {
        Invoice invoice = new Invoice();
        invoice.setNumber("#Invoice-Number");
        invoice.setDate(LocalDate.of(2019, 06, 25));
        invoice.setCurrency("EUR");
        invoice.setLocale(Locale.FRANCE);
        invoice.setProvider(new Provider());
        invoice.getProvider().setCorporateName("CorporateName");
        invoice.setSender(new Contact());
        invoice.getSender().setAddress(new Address());
        invoice.getSender().getAddress().setIdentification("identificationAddessSender");
        invoice.setRecipient(new Contact());
        invoice.getRecipient().setAddress(new Address());
        invoice.getRecipient().getAddress().setIdentification("identificationAddessRecipient");
        invoice.setOrder(new Order());
        invoice.getOrder().setDescription("description with accents special caracters &é'(§è!çà)-\\\"");
        invoice.setPaymentInstructions(new PaymentInstructions());
        invoice.getPaymentInstructions().setAmount(BigDecimal.valueOf(1386.26f));
        invoice.getPaymentInstructions().setDueDate(LocalDate.of(2019, 07, 25));
        return invoice;
    }

}
