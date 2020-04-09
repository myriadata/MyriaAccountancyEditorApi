package fr.myriadata.myriainvoice.api.service.layout.table;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.service.layout.text.BoldText;

import java.io.IOException;

public class HeaderCell extends Cell {
    public HeaderCell(String label) throws IOException {
        setTextAlignment(TextAlignment.CENTER);
        add(new Paragraph(new BoldText(label)));
    }
}
