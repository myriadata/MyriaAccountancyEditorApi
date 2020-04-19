package fr.myriadata.myriainvoice.api.service.invoice.pdf.table;

import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.format.AmountFormat;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.paragraph.NullableParagraph;

import java.math.BigDecimal;
import java.util.Locale;

public class AmountCell extends BorderedCell {

    public AmountCell(BigDecimal amount, Locale locale) {
        this(amount, locale, null);
    }

    public AmountCell(BigDecimal amount, Locale locale, String currency) {
        setTextAlignment(TextAlignment.RIGHT);
        setPaddingRight(5f);

        add(new NullableParagraph(new AmountFormat(locale, currency).format(amount)));
    }

}
