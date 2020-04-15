package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;

public class ChequePaymentInstructionsDiv extends MethodPaymentInstructionsDiv {

    public ChequePaymentInstructionsDiv(PaymentMethod paymentMethod, PaymentInstructions paymentInstructions) {
        super(paymentMethod, paymentInstructions);

        add(new Paragraph().setMultipliedLeading(1)
            .add("Ordre : " + paymentInstructions.getChequePaymentInstructions().getPayee() + "\n")
        );
    }

}
