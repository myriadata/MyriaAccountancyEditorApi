package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class AmountFormatTest {

    @Test
    public void shouldReturnNullWhenAmountIsNull() {
        // GIVEN
        AmountFormat format = new AmountFormat(Locale.FRANCE);

        // WHEN
        String result = format.format(null);

        // THEN
        Assertions.assertNull(result);
    }

    @Test
    public void shouldReturnNullWhenAmountIsNullEvenIfCurrencyIsSet() {
        // GIVEN
        AmountFormat format = new AmountFormat(Locale.FRANCE);

        // WHEN
        String result = format.format(null);

        // THEN
        Assertions.assertNull(result);
    }

    @Test
    public void shouldReturnAmountWithoutCurrency() {
        // GIVEN
        AmountFormat format = new AmountFormat(Locale.FRANCE);

        // WHEN
        String result = format.format(BigDecimal.valueOf(1337.42));

        // THEN
        Assertions.assertNotNull(result);
        Assertions.assertEquals("1 337,42", result);
    }

    @Test
    public void shouldReturnAmountWithCurrency() {
        // GIVEN
        AmountFormat format = new AmountFormat(Locale.FRANCE, Currency.getInstance("EUR"));

        // WHEN
        String result = format.format(BigDecimal.valueOf(1337.42));

        // THEN
        Assertions.assertNotNull(result);
        Assertions.assertEquals("1 337,42 €", result);
    }

}
