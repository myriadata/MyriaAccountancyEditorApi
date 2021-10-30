package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriaaccountancyeditor.api.model.payment.PaymentInstructions;
import fr.myriadata.myriaaccountancyeditor.api.model.payment.PaymentMethod;

import java.util.Locale;

public class MethodPaymentInstructionsDiv extends Div {

    /*
        Not used paymentMethod and paymentInstructions parameter exists because of constructor is call by recursive way
        and this call give this two parameters.
     */
    public MethodPaymentInstructionsDiv(PaymentMethod paymentMethod, PaymentInstructions paymentInstructions, Locale locale) {
        add(new Paragraph().setMultipliedLeading(1).add(" - " + paymentMethod.getLabel(locale)));
    }

}
