package fr.myriadata.myriainvoice.api.service.invoice.pdf.format;

import fr.myriadata.myriainvoice.api.service.i18n.I18nService;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public class DateFormat {

    private DateFormatSymbols dateFormatSymbols;

    public DateFormat(Locale locale) {
        this.dateFormatSymbols = I18nService.getDateFormatSymbols(locale);
    }

    public String format(LocalDate date) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(date.getDayOfMonth());
        stringBuffer.append(" ");
        stringBuffer.append(dateFormatSymbols.getShortMonths()[date.getMonthValue() - 1]);
        stringBuffer.append(" ");
        stringBuffer.append(date.getYear());

        return stringBuffer.toString();
    }

}
