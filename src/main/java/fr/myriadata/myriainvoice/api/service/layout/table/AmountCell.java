package fr.myriadata.myriainvoice.api.service.layout.table;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.service.layout.format.AmountFormat;

import java.math.BigDecimal;

public class AmountCell extends BorderedCell {

    public AmountCell(BigDecimal amount) {
        setTextAlignment(TextAlignment.RIGHT);
        add(new Paragraph(new AmountFormat().format(amount)));
    }

}
