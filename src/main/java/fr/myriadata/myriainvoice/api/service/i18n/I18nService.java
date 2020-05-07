package fr.myriadata.myriainvoice.api.service.i18n;

import fr.myriadata.myriainvoice.api.conf.graalvm.formatter.DateFormatSymbolsSupport;
import fr.myriadata.myriainvoice.api.conf.graalvm.formatter.NumberFormatSupport;
import fr.myriadata.myriainvoice.api.conf.graalvm.i18n.ResourceBundleSupport;
import org.graalvm.nativeimage.ImageSingletons;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
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

    public static DateFormatSymbols getDateFormatSymbols(Locale locale) {
        return ImageSingletons.contains(DateFormatSymbolsSupport.class)
                ? ImageSingletons.lookup(DateFormatSymbolsSupport.class).get(locale)
                : DateFormatSymbols.getInstance(locale);
    }

}
