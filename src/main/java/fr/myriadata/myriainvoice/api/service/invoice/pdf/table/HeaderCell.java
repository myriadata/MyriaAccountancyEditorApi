package fr.myriadata.myriainvoice.api.service.invoice.pdf.table;

import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;

public class HeaderCell extends Cell {
    public HeaderCell(String label) throws IOException {
        setTextAlignment(TextAlignment.CENTER);
        setPaddingRight(5f);
        setBorder(new SolidBorder(.1f));
        setBorderTop(new SolidBorder(1));
        setBorderBottom(new SolidBorder(1));

        add(new Paragraph(new BoldText(label)));
    }
}
