package fr.myriadata.myriainvoice.api.service.layout.table;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;

public class UnborderedCell extends Cell {

    public UnborderedCell() {
        super();
        setBorder(Border.NO_BORDER);
    }
}
