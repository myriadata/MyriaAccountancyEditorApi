package fr.myriadata.myriainvoice.api.service.invoice.pdf.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import fr.myriadata.myriainvoice.api.model.order.InvoiceLine;
import fr.myriadata.myriainvoice.api.model.order.Order;
import fr.myriadata.myriainvoice.api.service.i18n.I18nService;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.format.AmountFormat;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.paragraph.NullableParagraph;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.AmountCell;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.BorderedTable;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.HeaderCell;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.text.ObliqueText;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.BorderedCell;

import java.io.IOException;
import java.util.Locale;

public class OrderDiv extends Div {


    public OrderDiv(Order order, Locale locale, String currency) throws IOException {
        add(new Paragraph().setMultipliedLeading(1)
            .add(new ObliqueText(String.format("%s %s %s\n",
                    I18nService.get("invoice.order.number", locale),
                    I18nService.get("common.operator.assignment", locale),
                    order.getNumber())))
            .add(new ObliqueText(String.format("%s %s %s",
                    I18nService.get("invoice.order.reference", locale),
                    I18nService.get("common.operator.assignment", locale),
                    order.getCustomerReference()))));

        add(new Paragraph(order.getDescription()));
        add(expenses(order, locale, currency));
    }

    private Table expenses(Order order, Locale locale, String currency) throws IOException {
        Table table = new BorderedTable(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(55f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
                new UnitValue(UnitValue.createPercentValue(15f)),
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100));

        table.addHeaderCell(new HeaderCell(I18nService.get("invoice.order.line.description", locale)).setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(new HeaderCell(I18nService.get("invoice.order.line.quantity", locale)))
                .addHeaderCell(new HeaderCell(I18nService.get("invoice.order.line.price", locale)))
                .addHeaderCell(new HeaderCell(I18nService.get("invoice.order.line.amount", locale)));

        for (InvoiceLine line : order.getLines()) {
            table.addCell(new BorderedCell().add(new NullableParagraph(line.getDescription())));
            table.addCell(new BorderedCell().add(new NullableParagraph(new AmountFormat(locale).format(line.getQuantity()))
                    .setTextAlignment(TextAlignment.RIGHT)));
            table.addCell(new AmountCell(line.getUnitPrice(), locale, currency));
            table.addCell(new AmountCell(line.getAmount(), locale, currency));
        }

        return table;
    }

}
