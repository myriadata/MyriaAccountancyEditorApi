package fr.myriadata.myriainvoice.api.service.layout.table;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.util.List;
import java.util.stream.IntStream;

public class CustomPageTable extends Table {

    public CustomPageTable(int numColumns, List<IBlockElement> contents) {
        super(numColumns);

        if (contents.size() != numColumns) {
            String message = "The number of columns in Table (" + numColumns
                    + ") constructor must be equal to the number of content (" + contents.size() + ")";
            throw new IllegalArgumentException(message);
        }

        setWidth(new UnitValue(UnitValue.PERCENT, 100));

        int columnWidth = 100 / numColumns >= 33 ? 100 / numColumns : 33;
        IntStream.range(0, numColumns).forEach((i) -> addCell(new Cell()
                .setWidth(new UnitValue(UnitValue.PERCENT, columnWidth))
                .setBorder(Border.NO_BORDER)
                .add(contents.get(i))));
    }

}
