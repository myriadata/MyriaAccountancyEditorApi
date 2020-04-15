package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import fr.myriadata.myriainvoice.api.model.order.InvoiceLine;
import fr.myriadata.myriainvoice.api.model.order.Order;
import fr.myriadata.myriainvoice.api.service.layout.format.AmountFormat;
import fr.myriadata.myriainvoice.api.service.layout.paragraph.NullableParagraph;
import fr.myriadata.myriainvoice.api.service.layout.table.AmountCell;
import fr.myriadata.myriainvoice.api.service.layout.table.BorderedCell;
import fr.myriadata.myriainvoice.api.service.layout.table.BorderedTable;
import fr.myriadata.myriainvoice.api.service.layout.table.HeaderCell;
import fr.myriadata.myriainvoice.api.service.layout.text.ObliqueText;

import java.io.IOException;
import java.text.DecimalFormat;

public class OrderDiv extends Div {


    public OrderDiv(Order order) throws IOException {
        add(new Paragraph().setMultipliedLeading(1)
            .add(new ObliqueText(String.format("Numéro de commande : %s\n", order.getNumber())))
            .add(new ObliqueText(String.format("Référence cliente : %s", order.getCustomerReference()))));

        add(new Paragraph(order.getDescription()));
        add(expenses(order));
    }

    private Table expenses(Order order) throws IOException {
        Table table = new BorderedTable(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(55f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100));

        table.addHeaderCell(new HeaderCell("Désignation").setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(new HeaderCell("Quantité"))
                .addHeaderCell(new HeaderCell("Prix unitaire"))
                .addHeaderCell(new HeaderCell("Montant H.T"));

        for (InvoiceLine line : order.getLines()) {
            table.addCell(new BorderedCell().add(new NullableParagraph(line.getDescription())));
            table.addCell(new BorderedCell().add(new NullableParagraph(
                    line.getQuantity() != null ? new DecimalFormat("##0.00").format(line.getQuantity()) : null))
                    .setTextAlignment(TextAlignment.RIGHT));
            table.addCell(new AmountCell(line.getUnitPrice()));
            table.addCell(new AmountCell(line.getAmount()));
        }

        return table;
    }

}
