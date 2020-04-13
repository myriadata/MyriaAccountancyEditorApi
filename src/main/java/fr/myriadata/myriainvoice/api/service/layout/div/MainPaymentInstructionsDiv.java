package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;
import fr.myriadata.myriainvoice.api.service.layout.format.AmountFormat;

import java.time.format.DateTimeFormatter;

public class MainPaymentInstructionsDiv extends Div {

    public MainPaymentInstructionsDiv(PaymentInstructions paymentInstructions) {
        add(new Paragraph()
                .setMultipliedLeading(1)
                .add(String.format("Montant net à payer : %s\n", new AmountFormat().format(paymentInstructions.getAmount())))
                .add(String.format("Date d'échéance : %s\n", paymentInstructions.getPaymentDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));

        if (paymentInstructions.getPaymentMethods().size() >= 1) {
            add(paymentMethods(paymentInstructions.getPaymentMethods()));
        }
    }

    private Paragraph paymentMethods(java.util.List<PaymentMethod> methods) {
        Paragraph paragraph = new Paragraph().setMultipliedLeading(1);
        paragraph.add("Mode" + (methods.size() >= 2 ? "s" : "") + " de règlement : \n");

        List methodList = new List();
        for (PaymentMethod method : methods) {
            methodList.add(new ListItem(method.getLabel()));
        }
        paragraph.add(methodList);
        return paragraph;
    }

}
