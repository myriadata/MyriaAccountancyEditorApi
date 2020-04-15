package fr.myriadata.myriainvoice.api.service.layout.div;

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
import fr.myriadata.myriainvoice.api.service.layout.table.AmountCell;
import fr.myriadata.myriainvoice.api.service.layout.table.BorderedTable;
import fr.myriadata.myriainvoice.api.service.layout.table.BorderedCell;
import fr.myriadata.myriainvoice.api.service.layout.table.HeaderCell;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;


public class TaxDiv extends Div {

    public TaxDiv(ConsolidatedTaxes consolidatedTaxes, String currency) throws IOException {
        add(tax(consolidatedTaxes.getByAmount(), currency));
        add(totalTax(consolidatedTaxes.getTotal(), currency));
    }

    private Table tax(Map<BigDecimal, ValueAddedTax> consolidatedTaxesByAmount, String currency) throws IOException {
        Table table = new BorderedTable(new UnitValue[] {
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
            table.addCell(new BorderedCell().add(new Paragraph(new DecimalFormat(
                    allTaxAreInteger(consolidatedTaxesByAmount.keySet()) ? "##0" : "##0.00")
                    .format(valueAddedTaxByAmount.getKey()) + " %"))).setTextAlignment(TextAlignment.RIGHT);
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getBaseAmount(), currency));
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getTaxAmount(), currency));
            table.addCell(new AmountCell(valueAddedTaxByAmount.getValue().getIncludingTaxAmount(), currency));
        }

        return table;
    }

    private Table totalTax(ValueAddedTax valueAddedTax, String currency) throws IOException {
        Table table = new BorderedTable(new UnitValue[] {
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f)),
                new UnitValue(UnitValue.createPercentValue(28f))
        }).setWidth(new UnitValue(UnitValue.PERCENT, 84))
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setBorderTop(Border.NO_BORDER);

        table.addCell(new AmountCell(valueAddedTax.getBaseAmount(), currency)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));
        table.addCell(new AmountCell(valueAddedTax.getTaxAmount(), currency)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));
        table.addCell(new AmountCell(valueAddedTax.getIncludingTaxAmount(), currency)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)));

        return table;
    }

    private boolean allTaxAreInteger(Collection<BigDecimal> taxes) {
        for (BigDecimal tax : taxes) {
            if (tax.stripTrailingZeros().scale() > 0) {
                return false;
            }
        }

        return true;
    }

}
