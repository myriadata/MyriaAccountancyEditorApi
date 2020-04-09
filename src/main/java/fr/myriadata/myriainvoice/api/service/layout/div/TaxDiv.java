package fr.myriadata.myriainvoice.api.service.layout.div;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import fr.myriadata.myriainvoice.api.model.tax.ConsolidatedTaxes;
import fr.myriadata.myriainvoice.api.model.tax.ValueAddedTax;
import fr.myriadata.myriainvoice.api.service.layout.table.AmountCell;
import fr.myriadata.myriainvoice.api.service.layout.table.HeaderCell;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;


public class TaxDiv extends Div {

    public TaxDiv(ConsolidatedTaxes consolidatedTaxes) throws IOException {
        add(tax(consolidatedTaxes.getByAmount()));
        add(totalTax(consolidatedTaxes.getTotal()));
    }

    private Table tax(Map<BigDecimal, ValueAddedTax> consolidatedTaxesByAmount) throws IOException {
        Table table = new Table(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(16f)),
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 100));

        table.addHeaderCell(new HeaderCell("TVA"))
                .addHeaderCell(new HeaderCell("Montant HT"))
                .addHeaderCell(new HeaderCell("Montant TVA"))
                .addHeaderCell(new HeaderCell("Montant TTC"));

        for (Map.Entry<BigDecimal, ValueAddedTax> valueAddedTaxByAmount : consolidatedTaxesByAmount.entrySet()) {
            table.addCell(new Cell().add(new Paragraph(new DecimalFormat("##0.00").format(valueAddedTaxByAmount.getKey()) + " %"))).setTextAlignment(TextAlignment.RIGHT);
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getBaseAmount()));
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getTaxAmount()));
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getIncludingTaxAmount()));
        }

        return table;
    }

    private Table totalTax(ValueAddedTax valueAddedTax) throws IOException {
        Table table = new Table(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 84))
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setBorderTop(Border.NO_BORDER);

        table.addCell(new AmountCell(valueAddedTax.getBaseAmount())
                .setBorderTop(Border.NO_BORDER).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));
        table.addCell(new AmountCell(valueAddedTax.getTaxAmount())
                .setBorderTop(Border.NO_BORDER).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));
        table.addCell(new AmountCell(valueAddedTax.getIncludingTaxAmount())
                .setBorderTop(Border.NO_BORDER).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));

        return table;
    }

}
