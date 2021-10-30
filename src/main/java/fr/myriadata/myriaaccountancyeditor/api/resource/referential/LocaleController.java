package fr.myriadata.myriaaccountancyeditor.api.resource.referential;

import fr.myriadata.myriaaccountancyeditor.api.service.referential.LocaleService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Locale;

@Path("/v1/locales")
public class LocaleController {

    @Inject
    LocaleService localeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Locale> get() {
        return localeService.get();
    }

}
