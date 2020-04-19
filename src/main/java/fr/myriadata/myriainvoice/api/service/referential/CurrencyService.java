package fr.myriadata.myriainvoice.api.service.referential;

import javax.enterprise.context.ApplicationScoped;
import java.util.Currency;
import java.util.Set;

@ApplicationScoped
public class CurrencyService {

    public Set<Currency> get() {
        return Currency.getAvailableCurrencies();
    }

}
