package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.table;

import com.itextpdf.layout.element.Div;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class FlexboxTableTest {

    @Test
    public void shouldThrowExceptionWhenZeroColumnforZeroContent() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FlexboxTable(0, null);
        });

        Assertions.assertEquals("The number of columns in Table constructor must be greater than zero", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenTwoColumnsforFourContents() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FlexboxTable(2, Arrays.asList(new Div[4]));
        });

        Assertions.assertEquals("The number of columns in Table (2) constructor " +
                "must be equal to the number of content (4)", exception.getMessage());
    }

}
