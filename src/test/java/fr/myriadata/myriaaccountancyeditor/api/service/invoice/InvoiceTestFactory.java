package fr.myriadata.myriaaccountancyeditor.api.service.invoice;

import fr.myriadata.myriaaccountancyeditor.api.model.Invoice;
import fr.myriadata.myriaaccountancyeditor.api.model.common.Address;
import fr.myriadata.myriaaccountancyeditor.api.model.order.Order;
import fr.myriadata.myriaaccountancyeditor.api.model.party.Customer;
import fr.myriadata.myriaaccountancyeditor.api.model.party.Provider;
import fr.myriadata.myriaaccountancyeditor.api.model.payment.PaymentInstructions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;

public class InvoiceTestFactory {

    private InvoiceTestFactory() {}

    public static Invoice createLightInvoice() {
        Invoice invoice = new Invoice();
        invoice.setNumber("#Invoice-Number");
        invoice.setDate(LocalDate.of(2019, 06, 25));
        invoice.setCurrency(Currency.getInstance("EUR"));
        invoice.setLocale(Locale.FRANCE);
        invoice.setProvider(new Provider());
        invoice.getProvider().setCorporateName("CorporateName");
        invoice.getProvider().setAddress(new Address());
        invoice.getProvider().getAddress().setIdentification("providerAddress");
        invoice.setCustomer(new Customer());
        invoice.getCustomer().setAddress(new Address());
        invoice.getCustomer().getAddress().setIdentification("identificationAddressCustomer");
        invoice.setOrder(new Order());
        invoice.getOrder().setDescription("description with accents special caracters &é'(§è!çà)-\\\"");
        invoice.setPaymentInstructions(new PaymentInstructions());
        invoice.getPaymentInstructions().setAmount(BigDecimal.valueOf(1386.26f));
        invoice.getPaymentInstructions().setDueDate(LocalDate.of(2019, 07, 25));
        return invoice;
    }

}
