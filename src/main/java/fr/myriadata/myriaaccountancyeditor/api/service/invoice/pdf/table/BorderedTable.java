package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.table;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

public class BorderedTable extends Table {

    public BorderedTable(UnitValue[] columnWidths) {
        super(columnWidths);

        Border border = new SolidBorder(1);
        setBorder(border);
    }
}
