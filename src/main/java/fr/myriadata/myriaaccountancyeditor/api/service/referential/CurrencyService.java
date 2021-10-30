package fr.myriadata.myriaaccountancyeditor.api.service.referential;

import javax.enterprise.context.ApplicationScoped;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CurrencyService {

    public List<Currency> get() {
        return Currency.getAvailableCurrencies().stream()
                .sorted(Comparator.comparing(Currency::getCurrencyCode))
                .collect(Collectors.toList());
    }

}
