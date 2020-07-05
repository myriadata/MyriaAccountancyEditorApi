package fr.myriadata.myriainvoice.api.service.invoice.pdf.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

public class DateFormatTest {

    @Test
    public void shouldReturnDateAsStringInFrench() {
        // GIVEN
        DateFormat dateFormat = new DateFormat(Locale.FRANCE);

        // WHEN
        String date = dateFormat.format(LocalDate.of(2019, 07, 25));

        // THEN
        Assertions.assertNotNull(date);
        Assertions.assertEquals("25 juil. 2019", date);
    }

    @Test
    public void shouldReturnDateAsStringInEnglish() {
        // GIVEN
        DateFormat dateFormat = new DateFormat(Locale.ENGLISH);

        // WHEN
        String date = dateFormat.format(LocalDate.of(2019, 07, 25));

        // THEN
        Assertions.assertNotNull(date);
        Assertions.assertEquals("Jul 25, 2019", date);
    }

}
