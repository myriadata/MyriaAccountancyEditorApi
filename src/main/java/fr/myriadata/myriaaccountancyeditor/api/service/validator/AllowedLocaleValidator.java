package fr.myriadata.myriaaccountancyeditor.api.service.validator;

import fr.myriadata.myriaaccountancyeditor.api.service.referential.LocaleService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

@ApplicationScoped
public class AllowedLocaleValidator implements ConstraintValidator<AllowedLocale, Locale> {

    @Inject
    LocaleService localeService;

    @Override
    public boolean isValid(Locale locale, ConstraintValidatorContext constraintValidatorContext) {
        return localeService.get().contains(locale);
    }

}
