package fr.myriadata.myriainvoice.api.service.invoice.pdf.format;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class AmountFormat {

    private NumberFormat numberFormat;

    public AmountFormat(Locale locale) {
        this.numberFormat = NumberFormat.getNumberInstance(locale);
        setFractionDigits(2);
    }

    public AmountFormat(Locale locale, int fractionDigits) {
        this(locale);
        setFractionDigits(fractionDigits);
    }

    public AmountFormat(Locale locale, Currency currency) {
        this.numberFormat = NumberFormat.getCurrencyInstance(locale);
        this.numberFormat.setCurrency(currency);
        setFractionDigits(2);
    }

    public String format(BigDecimal amount) {
        if (amount == null) {
            return null;
        }

        return numberFormat.format(amount);
    }



    private void setFractionDigits(int fractionDigits) {
        this.numberFormat.setMinimumFractionDigits(fractionDigits);
        this.numberFormat.setMaximumFractionDigits(fractionDigits);
    }
}
