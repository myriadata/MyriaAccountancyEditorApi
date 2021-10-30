package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.div;

import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriaaccountancyeditor.api.model.payment.PaymentInstructions;
import fr.myriadata.myriaaccountancyeditor.api.model.payment.PaymentMethod;
import fr.myriadata.myriaaccountancyeditor.api.service.i18n.I18nService;

import java.util.Locale;
import java.util.Objects;

public class ChequePaymentInstructionsDiv extends MethodPaymentInstructionsDiv {

    public ChequePaymentInstructionsDiv(PaymentMethod paymentMethod, PaymentInstructions paymentInstructions, Locale locale) {
        super(paymentMethod, paymentInstructions, locale);

        if (Objects.nonNull(paymentInstructions.getChequePaymentInstructions())) {
            if (Objects.nonNull(paymentInstructions.getChequePaymentInstructions().getPayee())) {
                add(new Paragraph().setMultipliedLeading(1)
                        .add(String.format("%s %s %s",
                                I18nService.getText("invoice.payment.method.cheque.recipient", locale),
                                I18nService.getText("common.operator.assignment", locale),
                                paymentInstructions.getChequePaymentInstructions().getPayee() + "\n")));
            }
        }
    }

}
