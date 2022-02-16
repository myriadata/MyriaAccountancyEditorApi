package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import fr.myriadata.myriaaccountancyeditor.api.model.order.Order;
import fr.myriadata.myriaaccountancyeditor.api.model.order.OrderLine;
import fr.myriadata.myriaaccountancyeditor.api.service.i18n.I18nService;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.constant.PdfConstants;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.format.AmountFormat;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.paragraph.NullableParagraph;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.table.AmountCell;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.table.BorderedCell;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.table.BorderedTable;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.table.HeaderCell;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.text.ObliqueText;

import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class OrderDiv extends Div {

    public OrderDiv(Order order, Locale locale, Currency currency) throws IOException {
        setMarginBottom(PdfConstants.TEXT_FONT_SIZE);

        if (Objects.nonNull(order)) {
            Paragraph orderIdParagraph = new Paragraph().setMultipliedLeading(1);
            if (Objects.nonNull(order.getNumber())) {
                orderIdParagraph.add(new ObliqueText(String.format("%s %s %s\n",
                        I18nService.getText("invoice.order.number", locale),
                        I18nService.getText("common.operator.assignment", locale),
                        order.getNumber())));
            }
            if (Objects.nonNull(order.getCustomerReference())) {
                orderIdParagraph.add(new ObliqueText(String.format("%s %s %s",
                        I18nService.getText("invoice.order.reference", locale),
                        I18nService.getText("common.operator.assignment", locale),
                        order.getCustomerReference())));
            }
            if (!orderIdParagraph.isEmpty()) {
                add(orderIdParagraph);
            }

            if (Objects.nonNull(order.getDescription())) {
                add(new Paragraph(order.getDescription()));
            }

            if (Objects.nonNull(order.getLines()) && !order.getLines().isEmpty()) {
                add(orderLines(order.getLines(), locale, currency));
            }
        }
    }

    private Table orderLines(List<OrderLine> lines, Locale locale, Currency currency) throws IOException {
        Table table = new BorderedTable(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(56f)),
                new UnitValue(UnitValue.createPercentValue(10f)),
                new UnitValue(UnitValue.createPercentValue(10f)),
                new UnitValue(UnitValue.createPercentValue(12f)),
                new UnitValue(UnitValue.createPercentValue(12f)),
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100));

        table.addHeaderCell(new HeaderCell(I18nService.getText("invoice.order.line.description", locale)).setTextAlignment(TextAlignment.LEFT))
                .addHeaderCell(new HeaderCell(I18nService.getText("invoice.order.line.unit", locale)))
                .addHeaderCell(new HeaderCell(I18nService.getText("invoice.order.line.quantity", locale)))
                .addHeaderCell(new HeaderCell(I18nService.getText("invoice.order.line.price", locale)))
                .addHeaderCell(new HeaderCell(I18nService.getText("invoice.order.line.amount", locale)));

        for (OrderLine line : lines) {
            table.addCell(new BorderedCell().add(new NullableParagraph(line.getDescription())));
            table.addCell(new BorderedCell().add(new NullableParagraph(line.getUnit()).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new BorderedCell().add(new NullableParagraph(new AmountFormat(locale).format(line.getQuantity())).setTextAlignment(TextAlignment.RIGHT)));
            table.addCell(new AmountCell(line.getUnitPrice(), locale, currency));
            table.addCell(new AmountCell(line.getAmount(), locale, currency));
        }

        return table;
    }

}
