package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.format;

import fr.myriadata.myriaaccountancyeditor.api.service.i18n.I18nService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class AmountFormat {

    private NumberFormat numberFormat;

    public AmountFormat(Locale locale) {
        this.numberFormat = I18nService.getNumberFormat(locale);
        this.numberFormat.setMinimumFractionDigits(2);
        this.numberFormat.setMaximumFractionDigits(2);
    }

    public AmountFormat(Locale locale, int fractionDigits) {
        this(locale);
        this.numberFormat.setMinimumFractionDigits(fractionDigits);
        this.numberFormat.setMaximumFractionDigits(fractionDigits);
    }

    public AmountFormat(Locale locale, Currency currency) {
        this.numberFormat = I18nService.getCurrencyFormat(locale, currency);
        this.numberFormat.setMinimumFractionDigits(2);
        this.numberFormat.setMaximumFractionDigits(2);
    }

    public String format(BigDecimal amount) {
        if (amount == null) {
            return null;
        }

        return numberFormat.format(amount);
    }

}
