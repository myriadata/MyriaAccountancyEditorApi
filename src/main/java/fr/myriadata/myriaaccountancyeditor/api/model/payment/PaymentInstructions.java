package fr.myriadata.myriaaccountancyeditor.api.model.payment;

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
    private LocalDate dueDate;

    private List<String> terms;
    private List<PaymentMethod> paymentMethods;
    private ChequePaymentInstructions chequePaymentInstructions;
    private TransferPaymentInstructions transferPaymentInstructions;

}
