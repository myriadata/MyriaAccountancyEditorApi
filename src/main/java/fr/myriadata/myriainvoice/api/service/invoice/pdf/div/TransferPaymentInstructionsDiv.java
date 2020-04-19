package fr.myriadata.myriainvoice.api.service.invoice.pdf.div;

import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;
import fr.myriadata.myriainvoice.api.service.i18n.I18nService;

import java.util.Locale;

public class TransferPaymentInstructionsDiv extends MethodPaymentInstructionsDiv {

    public TransferPaymentInstructionsDiv(PaymentMethod paymentMethod, PaymentInstructions paymentInstructions, Locale locale) {
        super(paymentMethod, paymentInstructions, locale);

        add(new Paragraph().setMultipliedLeading(1)
                .add(String.format("%s %s %s\n",
                        I18nService.get("invoice.payment.method.transfer.reference", locale),
                        I18nService.get("common.operator.assignment", locale),
                        paymentInstructions.getTransferPaymentInstructions().getReference()))
                .add(String.format("%s %s %s\n",
                        I18nService.get("invoice.payment.method.transfer.bank", locale),
                        I18nService.get("common.operator.assignment", locale),
                        paymentInstructions.getTransferPaymentInstructions().getBankName()))
                .add(String.format("%s %s %s\n",
                        I18nService.get("invoice.payment.method.transfer.iban", locale),
                        I18nService.get("common.operator.assignment", locale),
                        paymentInstructions.getTransferPaymentInstructions().getIban()))
                .add(String.format("%s %s %s\n",
                        I18nService.get("invoice.payment.method.transfer.bic", locale),
                        I18nService.get("common.operator.assignment", locale),
                        paymentInstructions.getTransferPaymentInstructions().getBic())));
    }

}

