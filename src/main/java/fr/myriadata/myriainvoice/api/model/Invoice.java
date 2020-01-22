package fr.myriadata.myriainvoice.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Invoice {

    private Provider provider;
    private Contact sender;
    private Contact recipient;
    private Address invoicingAddress;

    private String number;
    private String orderNumber;
    private String description;
    private LocalDate invoiceDate;
    private LocalDate supplyDate;

    private LocalDate paymentDeadline;
    private BigDecimal latePenaltyRate;
    private BigDecimal recoveryFlatRate;

    private List<InvoiceLine> lines;
    private List<AdditionalExpense> additionalExpenses;

    private List<String> variousParticulars;

}
