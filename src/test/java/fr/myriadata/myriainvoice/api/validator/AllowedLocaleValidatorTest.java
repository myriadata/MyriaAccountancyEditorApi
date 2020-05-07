package fr.myriadata.myriainvoice.api.validator;

import fr.myriadata.myriainvoice.api.service.validator.AllowedLocaleValidator;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Locale;

@QuarkusTest
public class AllowedLocaleValidatorTest {

    @Inject
    AllowedLocaleValidator validator;

    @Test
    public void should() {
        shouldNotValidate(null);
        shouldNotValidate(new Locale(""));
        shouldNotValidate(new Locale("de"));
        shouldNotValidate(new Locale("fr", "GROLAND"));

        shouldValidate(new Locale("fr"));
        shouldValidate(new Locale("en"));
        shouldValidate(new Locale("fr", "FR"));
    }

    private void shouldNotValidate(Locale locale) {
        // GIVEN locale
        // WHEN
        boolean valid = validator.isValid(locale, null);

        // THEN
        Assertions.assertFalse(valid, locale + " should not be valid");
    }

    private void shouldValidate(Locale locale) {
        // GIVEN locale
        // WHEN
        boolean valid = validator.isValid(locale, null);

        // THEN
        Assertions.assertTrue(valid, locale + " should be valid");
    }

}
