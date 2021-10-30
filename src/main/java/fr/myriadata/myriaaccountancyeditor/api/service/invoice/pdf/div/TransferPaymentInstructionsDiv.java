package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.div;

import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriaaccountancyeditor.api.model.payment.PaymentInstructions;
import fr.myriadata.myriaaccountancyeditor.api.model.payment.PaymentMethod;
import fr.myriadata.myriaaccountancyeditor.api.service.i18n.I18nService;

import java.util.Locale;
import java.util.Objects;

public class TransferPaymentInstructionsDiv extends MethodPaymentInstructionsDiv {

    public TransferPaymentInstructionsDiv(PaymentMethod paymentMethod, PaymentInstructions paymentInstructions, Locale locale) {
        super(paymentMethod, paymentInstructions, locale);

        if (Objects.nonNull(paymentInstructions.getTransferPaymentInstructions())) {
            Paragraph paragraph = new Paragraph();
            paragraph.setMultipliedLeading(1);

            if (Objects.nonNull(paymentInstructions.getTransferPaymentInstructions().getReference())) {
                paragraph.add(String.format("%s %s %s\n",
                        I18nService.getText("invoice.payment.method.transfer.reference", locale),
                        I18nService.getText("common.operator.assignment", locale),
                        paymentInstructions.getTransferPaymentInstructions().getReference()));
            }

            paragraph.add(String.format("%s %s %s\n",
                    I18nService.getText("invoice.payment.method.transfer.bank", locale),
                    I18nService.getText("common.operator.assignment", locale),
                    paymentInstructions.getTransferPaymentInstructions().getBankName()));

            paragraph.add(String.format("%s %s %s\n",
                    I18nService.getText("invoice.payment.method.transfer.iban", locale),
                    I18nService.getText("common.operator.assignment", locale),
                    paymentInstructions.getTransferPaymentInstructions().getIban()));

            paragraph.add(String.format("%s %s %s\n",
                    I18nService.getText("invoice.payment.method.transfer.bic", locale),
                    I18nService.getText("common.operator.assignment", locale),
                    paymentInstructions.getTransferPaymentInstructions().getBic()));

            add(paragraph);
        }
    }

}

