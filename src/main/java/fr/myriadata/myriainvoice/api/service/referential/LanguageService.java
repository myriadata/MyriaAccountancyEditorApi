package fr.myriadata.myriainvoice.api.service.referential;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class LanguageService {

    private static final List<String> allowedLanguages = List.of("en", "fr");

    public List<String> get() {
        return allowedLanguages;
    }

    public boolean isAllowed(Locale locale) {
        return allowedLanguages.contains(locale.getLanguage());
    }

}