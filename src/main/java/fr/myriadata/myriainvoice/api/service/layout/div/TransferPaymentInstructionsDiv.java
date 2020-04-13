package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;

public class TransferPaymentInstructionsDiv extends Div {

    public TransferPaymentInstructionsDiv(PaymentInstructions paymentInstructions) {
        add(new Paragraph().setMultipliedLeading(1)
                .add(PaymentMethod.TRANSFER.getLabel() + "\n\n")
                .add("Référence : " + paymentInstructions.getTransferPaymentInstructions().getReference() + "\n")
                .add("Banque : " + paymentInstructions.getTransferPaymentInstructions().getBankName() + "\n")
                .add("IBAN : " + paymentInstructions.getTransferPaymentInstructions().getIban() + "\n")
                .add("BIC : " + paymentInstructions.getTransferPaymentInstructions().getBic() + "\n"));
    }

}

