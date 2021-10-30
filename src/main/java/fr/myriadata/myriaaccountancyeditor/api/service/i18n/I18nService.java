package fr.myriadata.myriaaccountancyeditor.api.service.i18n;

import fr.myriadata.myriaaccountancyeditor.api.conf.graalvm.formatter.DateFormatterSupport;
import fr.myriadata.myriaaccountancyeditor.api.conf.graalvm.i18n.ResourceBundleSupport;
import fr.myriadata.myriaaccountancyeditor.api.conf.graalvm.formatter.NumberFormatSupport;
import org.graalvm.nativeimage.ImageSingletons;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18nService {

    public static String getText(String key, Locale locale) {
        if(ImageSingletons.contains(ResourceBundleSupport.class)){
            return ImageSingletons.lookup(ResourceBundleSupport.class).getBundle(locale).getString(key);
        }

        return ResourceBundle.getBundle("i18n/pdf", locale).getString(key);
    }

    public static NumberFormat getNumberFormat(Locale locale) {
        if (ImageSingletons.contains(NumberFormatSupport.class)) {
            return ImageSingletons.lookup(NumberFormatSupport.class).getNumberFormat(locale);
        }

        return NumberFormat.getNumberInstance(locale);
    }

    public static NumberFormat getCurrencyFormat(Locale locale, Currency currency) {
        if (ImageSingletons.contains(NumberFormatSupport.class)) {
            return ImageSingletons.lookup(NumberFormatSupport.class).getCurrencyFormat(locale, currency);
        }

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setCurrency(currency);
        return numberFormat;
    }

    public static DateTimeFormatter getDateFormatter(Locale locale) {
        return ImageSingletons.contains(DateFormatterSupport.class)
                ? ImageSingletons.lookup(DateFormatterSupport.class).get(locale)
                : DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale);
    }

}
