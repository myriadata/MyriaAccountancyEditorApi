package fr.myriadata.myriainvoice.api.service.invoice.pdf.div;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import fr.myriadata.myriainvoice.api.model.tax.ConsolidatedTaxes;
import fr.myriadata.myriainvoice.api.model.tax.ValueAddedTax;
import fr.myriadata.myriainvoice.api.service.i18n.I18nService;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.constant.PdfConstants;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.AmountCell;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.format.AmountFormat;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.BorderedCell;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.BorderedTable;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.HeaderCell;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class TaxDiv extends Div {

    public TaxDiv(ConsolidatedTaxes consolidatedTaxes, Locale locale, Currency currency) throws IOException {
        setMarginBottom(PdfConstants.TEXT_FONT_SIZE * 2);

        if (Objects.nonNull(consolidatedTaxes)) {
            add(tax(consolidatedTaxes.getByAmount(), locale, currency));
            if (Objects.nonNull(consolidatedTaxes.getTotal())) {
                add(totalTax(consolidatedTaxes.getTotal(), locale, currency));
            }
        }
    }

    private Table tax(Map<BigDecimal, ValueAddedTax> consolidatedTaxesByAmount, Locale locale, Currency currency) throws IOException {
        Table table = new BorderedTable(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(16f)),
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100));

        table.addHeaderCell(new HeaderCell(I18nService.getText("invoice.tax.vat", locale)))
                .addHeaderCell(new HeaderCell(I18nService.getText("invoice.tax.amount.base", locale)))
                .addHeaderCell(new HeaderCell(I18nService.getText("invoice.tax.amount.vat", locale)))
                .addHeaderCell(new HeaderCell(I18nService.getText("invoice.tax.amount.total", locale)));

        for (Map.Entry<BigDecimal, ValueAddedTax> valueAddedTaxByAmount : consolidatedTaxesByAmount.entrySet()) {
            table.addCell(new BorderedCell().add(new Paragraph(new AmountFormat(locale,
                    fractionDigits(consolidatedTaxesByAmount.keySet()))
                    .format(valueAddedTaxByAmount.getKey()) + " %"))).setTextAlignment(TextAlignment.RIGHT);
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getBaseAmount(), locale, currency));
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getTaxAmount(), locale, currency));
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getIncludingTaxAmount(), locale, currency));
        }

        return table;
    }

    private Table totalTax(ValueAddedTax valueAddedTax, Locale locale, Currency currency) throws IOException {
        Table table = new BorderedTable(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 84))
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setBorderTop(Border.NO_BORDER);

        table.addCell(new AmountCell(valueAddedTax.getBaseAmount(), locale, currency)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));
        table.addCell(new AmountCell(valueAddedTax.getTaxAmount(), locale, currency)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));
        table.addCell(new AmountCell(valueAddedTax.getIncludingTaxAmount(), locale, currency)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));

        return table;
    }

    private int fractionDigits(Collection<BigDecimal> taxes) {
        return taxes.stream()
                .map(BigDecimal::stripTrailingZeros)
                .map(BigDecimal::scale)
                .max(Comparator.comparing(Integer::valueOf)).get();
    }

}
