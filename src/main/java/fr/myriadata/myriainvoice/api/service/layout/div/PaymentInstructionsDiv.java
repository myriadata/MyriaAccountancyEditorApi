package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.model.payment.PaymentInstructions;
import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;
import fr.myriadata.myriainvoice.api.service.layout.table.CustomPageTable;
import fr.myriadata.myriainvoice.api.service.layout.text.BoldText;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentInstructionsDiv extends Div {
    private Map<PaymentMethod, Class<? extends Div>> mapPaymentMethodToDiv = Map.of(
        PaymentMethod.CHEQUE,   ChequePaymentInstructionsDiv.class,
        PaymentMethod.TRANSFER, TransferPaymentInstructionsDiv.class
    );

    private List<PaymentMethod> methodWithoutDiv = List.of(PaymentMethod.CASH);

    public PaymentInstructionsDiv(PaymentInstructions paymentInstructions) throws IOException {
        add(new Paragraph(new BoldText("Conditions et modalités de paiement")));

        int numColumns = numColumns(paymentInstructions);
        List<IBlockElement> contents = contents(paymentInstructions);

        add(new CustomPageTable(numColumns, contents));
        add(delayDiv());
    }

    private int numColumns(PaymentInstructions paymentInstructions) {
        return paymentInstructions.getPaymentMethods().contains(PaymentMethod.CASH)
            ? paymentInstructions.getPaymentMethods().size()
            : paymentInstructions.getPaymentMethods().size() + 1;
    }

    private List<IBlockElement> contents(PaymentInstructions paymentInstructions) {
        List<IBlockElement> contents = new ArrayList<>();
        contents.add(new MainPaymentInstructionsDiv(paymentInstructions));

        for (PaymentMethod method : paymentInstructions.getPaymentMethods()) {
            if (!methodWithoutDiv.contains(method)) {
                try {
                    contents.add(mapPaymentMethodToDiv.get(method).getConstructor(PaymentInstructions.class).newInstance(paymentInstructions));
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalStateException("Error while creating specialized payment instruction div from declared method payment", e);
                }
            }
        }

        return contents;
    }

    private Div delayDiv() {
        return new Div().add(new Paragraph()
                .setMultipliedLeading(1)
                .setTextAlignment(TextAlignment.JUSTIFIED)
                .add("En cas de retard de paiement, seront exigibles, conformément à l'article L 441-6 du code de commerce, une " +
                    "indemnité calculée sur la base de trois fois le taux de l'intérêt légal en vigueur ainsi qu'une indemnité forfaitaire pour " +
                    "frais de recouvrement de 40 euros."));
    }

}
