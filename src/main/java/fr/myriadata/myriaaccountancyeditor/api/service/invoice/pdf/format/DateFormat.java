package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.format;

import fr.myriadata.myriaaccountancyeditor.api.service.i18n.I18nService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormat {

    private DateTimeFormatter dateFormatter;

    public DateFormat(Locale locale) {
        this.dateFormatter = I18nService.getDateFormatter(locale);
    }

    public String format(LocalDate date) {
        return dateFormatter.format(date);
    }

}
