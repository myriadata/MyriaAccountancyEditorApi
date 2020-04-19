package fr.myriadata.myriainvoice.api.service.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nService {

    public static String get(String key, Locale locale) {
        return ResourceBundle.getBundle("i18n/pdf", locale).getString(key);
    }

}
