package fr.myriadata.myriainvoice.api.service.invoice.pdf.div;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;
import fr.myriadata.myriainvoice.api.service.i18n.I18nService;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.constant.PdfConstants;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.format.DateFormat;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.*;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PaymentInstructionsDiv extends Div {
    private Map<PaymentMethod, Class<? extends Div>> mapPaymentMethodToDiv = Map.of(
        PaymentMethod.CASH,     MethodPaymentInstructionsDiv.class,
        PaymentMethod.CHEQUE,   ChequePaymentInstructionsDiv.class,
        PaymentMethod.TRANSFER, TransferPaymentInstructionsDiv.class
    );

    public PaymentInstructionsDiv(PaymentInstructions paymentInstructions, Locale locale, Currency currency) throws IOException {
        add(terms(paymentInstructions, locale, currency));
        if(Objects.nonNull(paymentInstructions.getPaymentMethods()) && !paymentInstructions.getPaymentMethods().isEmpty()) {
            add(methods(paymentInstructions, locale));
        }
    }

    private Table terms(PaymentInstructions paymentInstructions, Locale locale, Currency currency) throws IOException {
        Table contents = new Table(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(65f)),
                new UnitValue(UnitValue.createPercentValue(35f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100f));

        if (Objects.nonNull(paymentInstructions.getTerms()) && !paymentInstructions.getTerms().isEmpty()) {
            contents.addCell(new UnborderedCell().add(new TermsDiv(paymentInstructions.getTerms()))
                    .setTextAlignment(TextAlignment.JUSTIFIED)
                    .setFontSize(PdfConstants.TERM_FONT_SIZE));
        } else {
            contents.addCell(new UnborderedCell());
        }

        Table paymentTable = new BorderedTable(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(60f)),
                new UnitValue(UnitValue.createPercentValue(40f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100f))
          .setBackgroundColor(ColorConstants.LIGHT_GRAY, .6f);
        paymentTable.addCell(new BorderedCell().add(new Paragraph(new BoldText(I18nService.get("invoice.payment.payable", locale)))));
        paymentTable.addCell(new AmountCell(paymentInstructions.getAmount(), locale, currency)
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(2f));
        paymentTable.addCell(new BorderedCell().add(new Paragraph(new BoldText(I18nService.get("invoice.payment.date", locale)))));
        paymentTable.addCell(new BorderedCell().add(new Paragraph(new DateFormat(locale).format(paymentInstructions.getDueDate())))
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(2f));
        contents.addCell(new UnborderedCell().add(paymentTable));

        return contents;
    }

    private Div methods(PaymentInstructions paymentInstructions, Locale locale) {
        List<IBlockElement> paymentMethods = new ArrayList<>();

        for (PaymentMethod method : paymentInstructions.getPaymentMethods()) {
            try {
                paymentMethods.add(mapPaymentMethodToDiv.get(method)
                        .getConstructor(PaymentMethod.class, PaymentInstructions.class, Locale.class)
                        .newInstance(method, paymentInstructions, locale));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("Error while creating specialized payment instruction div from declared method payment", e);
            }
        }

        Div methodContents = new Div();
        methodContents.add(new Paragraph(String.format("%s %s",
                I18nService.get("invoice.payment.method" + (paymentMethods.size() >= 2 ? "s" : ""), locale),
                I18nService.get("common.operator.assignment", locale))));
        methodContents.add(new FlexboxTable(numColumns(paymentInstructions), paymentMethods));
        return methodContents;
    }

    private int numColumns(PaymentInstructions paymentInstructions) {
        return paymentInstructions.getPaymentMethods().contains(PaymentMethod.CASH)
                ? paymentInstructions.getPaymentMethods().size()
                : paymentInstructions.getPaymentMethods().size() + 1;
    }

}
