package fr.myriadata.myriainvoice.api.model.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PaymentInstructions {

    private BigDecimal amount;
    private LocalDate paymentDeadline;
    private List<PaymentMethod> paymentMethods;

    private ChequePaymentInstructions chequePaymentInstructions;
    private TransferPaymentInstructions transferPaymentInstructions;

}
