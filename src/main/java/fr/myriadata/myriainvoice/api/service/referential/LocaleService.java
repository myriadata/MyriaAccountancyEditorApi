package fr.myriadata.myriainvoice.api.service.referential;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ApplicationScoped
public class LocaleService {

    private List<String> availableLanguages = List.of("en", "fr");

    public List<Locale> get() {
        return Arrays.stream(Locale.getAvailableLocales())
                .filter(l -> availableLanguages.contains(l.getLanguage()))
                .collect(Collectors.toList());
    }

}
