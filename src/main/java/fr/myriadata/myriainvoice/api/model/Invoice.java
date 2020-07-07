package fr.myriadata.myriainvoice.api.model;

import fr.myriadata.myriainvoice.api.model.order.Order;
import fr.myriadata.myriainvoice.api.model.party.Customer;
import fr.myriadata.myriainvoice.api.model.party.Provider;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.tax.ConsolidatedTaxes;
import fr.myriadata.myriainvoice.api.service.validator.AllowedLocale;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;

@Getter
@Setter
public class Invoice {

    @NotBlank
    private String number;

    @NotNull
    private LocalDate date;

    @NotNull
    private Currency currency;

    @NotNull
    @AllowedLocale
    private Locale locale;

    @NotNull
    @Valid
    private Provider provider;

    @NotNull
    @Valid
    private Customer customer;

    @NotNull
    @Valid
    private Order order;

    @Valid
    private ConsolidatedTaxes consolidatedTaxes;

    @NotNull
    @Valid
    private PaymentInstructions paymentInstructions;

}
