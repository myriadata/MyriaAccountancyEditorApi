package fr.myriadata.myriainvoice.api.model;

import fr.myriadata.myriainvoice.api.model.common.Contact;
import fr.myriadata.myriainvoice.api.model.order.Order;
import fr.myriadata.myriainvoice.api.model.party.Provider;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.tax.ConsolidatedTaxes;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Invoice {

    private String number;
    private LocalDate date;
    private Provider provider;
    private Contact sender;
    private Contact recipient;

    private Order order;
    private ConsolidatedTaxes consolidatedTaxes;
    private List<String> variousParticulars;
    private PaymentInstructions paymentInstructions;

}
