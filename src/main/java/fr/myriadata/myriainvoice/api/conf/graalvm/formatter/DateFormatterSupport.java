package fr.myriadata.myriainvoice.api.conf.graalvm.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DateFormatterSupport {

    private Map<Locale, DateTimeFormatter> dateTimeFormatterByLocale;

    public DateFormatterSupport() {
        dateTimeFormatterByLocale = Arrays.stream(Locale.getAvailableLocales()).collect(Collectors.toMap(
            Function.identity(),
            l -> DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(l)));

        Arrays.stream(Locale.getAvailableLocales()).forEach(locale -> {
            dateTimeFormatterByLocale.get(locale).format(LocalDate.now());
        });
    }

    public DateTimeFormatter get(Locale locale) {
        return dateTimeFormatterByLocale.get(locale);
    }

}
