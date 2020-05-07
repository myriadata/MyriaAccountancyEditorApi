package fr.myriadata.myriainvoice.api.conf.graalvm.locale;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class LocaleSupport {

    private List<Locale> locales;

    public LocaleSupport() {
        this.locales = Arrays.stream(Locale.getAvailableLocales())
                .sorted(Comparator.comparing(Locale::toString))
                .collect(Collectors.toList());
    }

    public List<Locale> getAvailableLocales() {
        return locales;
    }

}
