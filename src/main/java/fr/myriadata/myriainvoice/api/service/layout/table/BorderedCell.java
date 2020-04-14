package fr.myriadata.myriainvoice.api.service.layout.table;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;

public class BorderedCell extends Cell {

    public BorderedCell() {
        setBorder(new SolidBorder(.1f));
    }

}
