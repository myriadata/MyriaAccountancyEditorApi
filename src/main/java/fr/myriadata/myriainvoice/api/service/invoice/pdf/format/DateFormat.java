package fr.myriadata.myriainvoice.api.service.invoice.pdf.format;

import fr.myriadata.myriainvoice.api.service.i18n.I18nService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormat {

    private DateTimeFormatter formatter;

    public DateFormat(Locale locale) {
        String datePattern = I18nService.get("common.date.format", locale);
        this.formatter = DateTimeFormatter.ofPattern(datePattern, locale);
    }

    public String format(LocalDate date) {
        return date.format(formatter);
    }

}
