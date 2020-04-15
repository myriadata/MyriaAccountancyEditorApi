package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;
import fr.myriadata.myriainvoice.api.service.layout.paragraph.MultiLineParagraph;
import fr.myriadata.myriainvoice.api.service.layout.table.*;
import fr.myriadata.myriainvoice.api.service.layout.text.BoldText;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentInstructionsDiv extends Div {
    private Map<PaymentMethod, Class<? extends Div>> mapPaymentMethodToDiv = Map.of(
        PaymentMethod.CASH,     MethodPaymentInstructionsDiv.class,
        PaymentMethod.CHEQUE,   ChequePaymentInstructionsDiv.class,
        PaymentMethod.TRANSFER, TransferPaymentInstructionsDiv.class
    );

    public PaymentInstructionsDiv(PaymentInstructions paymentInstructions) throws IOException {
        add(terms(paymentInstructions));
        add(methods(paymentInstructions));
        add(delayDiv());
    }

    private Table terms(PaymentInstructions paymentInstructions) throws IOException {
        Table contents = new Table(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(65f)),
                new UnitValue(UnitValue.createPercentValue(35f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100f));

        contents.addCell(new UnborderedCell().add(new MultiLineParagraph(paymentInstructions.getVariousTerms()))
            .setTextAlignment(TextAlignment.RIGHT)
            .setFontSize(8f));

        Table paymentTable = new BorderedTable(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(65f)),
                new UnitValue(UnitValue.createPercentValue(35f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100f));
        paymentTable.addCell(new BorderedCell().add(new Paragraph(new BoldText("Net à payer"))));
        paymentTable.addCell(new BorderedCell().add(new AmountCell(paymentInstructions.getAmount())));
        paymentTable.addCell(new BorderedCell().add(new Paragraph(new BoldText("Date d'échéance"))));
        paymentTable.addCell(new BorderedCell().add(new Paragraph(paymentInstructions.getPaymentDeadline()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))).setTextAlignment(TextAlignment.RIGHT));
        contents.addCell(new UnborderedCell().add(paymentTable));

        return contents;
    }

    private Div methods(PaymentInstructions paymentInstructions) {
        List<IBlockElement> paymentMethods = new ArrayList<>();

        for (PaymentMethod method : paymentInstructions.getPaymentMethods()) {
            try {
                paymentMethods.add(mapPaymentMethodToDiv.get(method)
                        .getConstructor(PaymentMethod.class, PaymentInstructions.class)
                        .newInstance(method, paymentInstructions));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("Error while creating specialized payment instruction div from declared method payment", e);
            }
        }

        Div methodContents = new Div();
        methodContents.add(new Paragraph("Méthode" + (paymentMethods.size() >= 2 ? "s" : "")  + " de paiment :"));
        methodContents.add(new FlexboxTable(numColumns(paymentInstructions), paymentMethods));
        return methodContents;
    }

    private Div delayDiv() {
        return new Div().add(new Paragraph()
                .setMultipliedLeading(1)
                .setTextAlignment(TextAlignment.JUSTIFIED)
                .setFontSize(8f)
                .add("En cas de retard de paiement, seront exigibles, conformément à l'article L 441-6 du code de commerce, une " +
                    "indemnité calculée sur la base de trois fois le taux de l'intérêt légal en vigueur ainsi qu'une indemnité forfaitaire pour " +
                    "frais de recouvrement de 40 euros."));
    }

    private int numColumns(PaymentInstructions paymentInstructions) {
        return paymentInstructions.getPaymentMethods().contains(PaymentMethod.CASH)
                ? paymentInstructions.getPaymentMethods().size()
                : paymentInstructions.getPaymentMethods().size() + 1;
    }

}
