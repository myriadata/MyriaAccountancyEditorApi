package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.table;

import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.util.List;
import java.util.stream.IntStream;

public class FlexboxTable extends Table {

    public FlexboxTable(int numColumns, List<IBlockElement> contents) {
        super(numColumns);

        if (contents.size() != numColumns) {
            String message = "The number of columns in Table (" + numColumns
                    + ") constructor must be equal to the number of content (" + contents.size() + ")";
            throw new IllegalArgumentException(message);
        }

        setWidth(new UnitValue(UnitValue.PERCENT, 100));
        IntStream.range(0, numColumns).forEach((i) -> addCell(new UnborderedCell()
                .setWidth(new UnitValue(UnitValue.PERCENT, 100 / numColumns))
                .add(contents.get(i))));
    }

}
