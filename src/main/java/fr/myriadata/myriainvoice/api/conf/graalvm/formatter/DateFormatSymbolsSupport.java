package fr.myriadata.myriainvoice.api.conf.graalvm.formatter;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DateFormatSymbolsSupport {

    private Map<Locale, DateFormatSymbols> dateFormatSymbolsByLocale;

    public DateFormatSymbolsSupport() {
        dateFormatSymbolsByLocale = Arrays.stream(Locale.getAvailableLocales()).collect(Collectors.toMap(
                Function.identity(),
                DateFormatSymbols::getInstance));
    }

    public DateFormatSymbols get(Locale locale) {
        return dateFormatSymbolsByLocale.get(locale);
    }

}
