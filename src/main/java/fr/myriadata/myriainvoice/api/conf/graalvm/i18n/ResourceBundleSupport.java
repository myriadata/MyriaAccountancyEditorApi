package fr.myriadata.myriainvoice.api.conf.graalvm.i18n;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResourceBundleSupport {

    private Map<Locale, ResourceBundle> resourcesByLocale;

    public ResourceBundleSupport() {
        resourcesByLocale = Arrays.stream(Locale.getAvailableLocales()).collect(
                Collectors.toMap(
                        Function.identity(),
                        l -> ResourceBundle.getBundle("i18n/pdf", l)));
    }

    public ResourceBundle getBundle(Locale locale) {
        return resourcesByLocale.get(locale);
    }

}
