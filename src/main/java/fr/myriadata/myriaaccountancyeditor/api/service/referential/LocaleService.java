package fr.myriadata.myriaaccountancyeditor.api.service.referential;

import fr.myriadata.myriaaccountancyeditor.api.conf.graalvm.locale.LocaleSupport;
import org.graalvm.nativeimage.ImageSingletons;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ApplicationScoped
public class LocaleService {

    @Inject
    LanguageService languageService;

    public List<Locale> get() {
        if (ImageSingletons.contains(LocaleSupport.class)){
            return filteredByLanguage(ImageSingletons.lookup(LocaleSupport.class).getAvailableLocales());
        }

        return filteredByLanguage(Arrays.asList(Locale.getAvailableLocales()).stream()
                .sorted(Comparator.comparing(Locale::toString))
                .collect(Collectors.toList()));
    }

    private List<Locale> filteredByLanguage(List<Locale> locales) {
        return locales.stream()
                .filter(languageService::isAllowed)
                .collect(Collectors.toList());
    }

}
