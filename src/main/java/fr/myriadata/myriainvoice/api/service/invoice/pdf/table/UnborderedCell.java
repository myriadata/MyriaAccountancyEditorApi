package fr.myriadata.myriainvoice.api.service.invoice.pdf.table;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;

public class UnborderedCell extends Cell {

    public UnborderedCell() {
        setPaddingRight(5f);
        setBorder(Border.NO_BORDER);
    }
}
