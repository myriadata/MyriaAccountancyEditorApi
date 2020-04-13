package fr.myriadata.myriainvoice.api.model.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferPaymentInstructions {

    private String bankName;
    private String iban;
    private String bic;
    private String reference;

}
