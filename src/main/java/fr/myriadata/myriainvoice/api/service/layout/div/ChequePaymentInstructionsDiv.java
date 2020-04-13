package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;

import java.io.IOException;

public class ChequePaymentInstructionsDiv extends Div {

    public ChequePaymentInstructionsDiv(PaymentInstructions paymentInstructions) throws IOException {
        add(new Paragraph().setMultipliedLeading(1)
            .add(PaymentMethod.CHEQUE.getLabel() + "\n\n")
            .add("Ordre : " + paymentInstructions.getChequePaymentInstructions().getPayee() + "\n")
        );
    }

}
