package fr.myriadata.myriainvoice.api.model.payment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PaymentInstructions {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDate paymentDeadline;

    private List<String> variousTerms;
    private List<PaymentMethod> paymentMethods;
    private ChequePaymentInstructions chequePaymentInstructions;
    private TransferPaymentInstructions transferPaymentInstructions;

}
