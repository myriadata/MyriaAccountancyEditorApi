package fr.myriadata.myriainvoice.api.service.invoice.pdf.table;

import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;

public class BorderedCell extends Cell {

    public BorderedCell() {
        setPaddingRight(5f);

        setBorder(new SolidBorder(.1f));
    }

}
