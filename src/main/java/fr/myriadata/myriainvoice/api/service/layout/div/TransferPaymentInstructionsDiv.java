package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;

public class TransferPaymentInstructionsDiv extends MethodPaymentInstructionsDiv {

    public TransferPaymentInstructionsDiv(PaymentMethod paymentMethod, PaymentInstructions paymentInstructions) {
        super(paymentMethod, paymentInstructions);

        add(new Paragraph().setMultipliedLeading(1)
                .add("Référence : " + paymentInstructions.getTransferPaymentInstructions().getReference() + "\n")
                .add("Banque : " + paymentInstructions.getTransferPaymentInstructions().getBankName() + "\n")
                .add("IBAN : " + paymentInstructions.getTransferPaymentInstructions().getIban() + "\n")
                .add("BIC : " + paymentInstructions.getTransferPaymentInstructions().getBic() + "\n"));
    }

}

