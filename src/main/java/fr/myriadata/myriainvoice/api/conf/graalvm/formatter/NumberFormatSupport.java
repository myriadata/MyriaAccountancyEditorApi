package fr.myriadata.myriainvoice.api.conf.graalvm.formatter;

import java.text.NumberFormat;
import java.util.*;

public class NumberFormatSupport {

    private Map<Locale, NumberFormat> numberInstancesByLocale;
    private Map<Locale, Map<String, NumberFormat>> currencyInstancesByLocale;

    public NumberFormatSupport() {
        numberInstancesByLocale = new HashMap<>();
        currencyInstancesByLocale = new HashMap<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            numberInstancesByLocale.put(locale, NumberFormat.getNumberInstance(locale));
            currencyInstancesByLocale.put(locale, new HashMap<>());
            for (Currency currency : Currency.getAvailableCurrencies()) {
                currencyInstancesByLocale.get(locale).put(currency.getCurrencyCode(), NumberFormat.getCurrencyInstance(locale));
                currencyInstancesByLocale.get(locale).get(currency.getCurrencyCode()).setCurrency(currency);
            }
        }
    }

    public NumberFormat getNumberFormat(Locale locale) {
        return (NumberFormat) numberInstancesByLocale.get(locale).clone();
    }

    public NumberFormat getCurrencyFormat(Locale locale, Currency currency) {
        return (NumberFormat) currencyInstancesByLocale.get(locale).get(currency.getCurrencyCode()).clone();
    }

}
