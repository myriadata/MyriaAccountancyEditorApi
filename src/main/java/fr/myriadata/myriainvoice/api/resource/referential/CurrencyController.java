package fr.myriadata.myriainvoice.api.resource.referential;

import fr.myriadata.myriainvoice.api.service.referential.CurrencyService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Currency;
import java.util.List;

@Path("/v1/currencies")
public class CurrencyController {

    @Inject
    CurrencyService currencyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Currency> get() {
        return currencyService.get();
    }

}
