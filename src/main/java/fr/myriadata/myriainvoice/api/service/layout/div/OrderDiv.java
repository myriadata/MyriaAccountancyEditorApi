package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import fr.myriadata.myriainvoice.api.model.order.AdditionalExpense;
import fr.myriadata.myriainvoice.api.model.order.InvoiceLine;
import fr.myriadata.myriainvoice.api.model.order.Order;
import fr.myriadata.myriainvoice.api.service.layout.format.AmountFormat;
import fr.myriadata.myriainvoice.api.service.layout.table.AmountCell;
import fr.myriadata.myriainvoice.api.service.layout.table.HeaderCell;
import fr.myriadata.myriainvoice.api.service.layout.text.ObliqueText;

import java.io.IOException;
import java.util.List;

public class OrderDiv extends Div {


    public OrderDiv(Order order) throws IOException {
        add(new Paragraph(new ObliqueText(String.format("Numéro de commande : %s", order.getNumber()))));

        add(new Paragraph(order.getDescription()));
        add(lines(order.getLines()));
        add(additionalExpenses(order.getAdditionalExpenses()));
    }

    private Table lines(List<InvoiceLine> invoiceLines) throws IOException {
        Table table = new Table(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(55f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100));

        table.addHeaderCell(new HeaderCell("Désignation").setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(new HeaderCell("Quantité"))
                .addHeaderCell(new HeaderCell("Prix unitaire"))
                .addHeaderCell(new HeaderCell("Montant H.T"));

        for (InvoiceLine line : invoiceLines) {
            table.addCell(new Cell().add(new Paragraph(line.getDescription())));
            table.addCell(new Cell().add(new Paragraph(new AmountFormat().format(line.getQuantity()))).setTextAlignment(TextAlignment.RIGHT));
            table.addCell(new AmountCell(line.getUnitPrice()));
            table.addCell(new AmountCell(line.getAmount()));
        }

        return table;
    }

    private Table additionalExpenses(List<AdditionalExpense> additionalExpenses) {
        Table table = new Table(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(30f)),
                new UnitValue(UnitValue.createPercentValue(15f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 45))
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setBorderTop(Border.NO_BORDER);

        for (AdditionalExpense additionalExpense : additionalExpenses) {
            table.addCell(new Cell().add(new Paragraph(additionalExpense.getLabel()))
                    .setTextAlignment(TextAlignment.RIGHT).setBorderTop(Border.NO_BORDER));
            table.addCell(new AmountCell(additionalExpense.getAmount()).setBorderTop(Border.NO_BORDER));
        }

        return table;
    }

}
