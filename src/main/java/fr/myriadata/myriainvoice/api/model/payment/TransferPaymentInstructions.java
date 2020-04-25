package fr.myriadata.myriainvoice.api.model.payment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransferPaymentInstructions {


    @NotBlank
    private String bankName;

    @NotBlank
    private String iban;

    @NotBlank
    private String bic;

    private String reference;

}
