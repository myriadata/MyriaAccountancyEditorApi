package fr.myriadata.myriainvoice.api.service.layout.table;

import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.service.layout.format.AmountFormat;
import fr.myriadata.myriainvoice.api.service.layout.paragraph.NullableParagraph;

import java.math.BigDecimal;

public class AmountCell extends BorderedCell {

    public AmountCell(BigDecimal amount, String currency) {
        setTextAlignment(TextAlignment.RIGHT);
        setPaddingRight(5f);

        add(new NullableParagraph(
                amount != null ? new AmountFormat(currency).format(amount) : null));
    }

}
