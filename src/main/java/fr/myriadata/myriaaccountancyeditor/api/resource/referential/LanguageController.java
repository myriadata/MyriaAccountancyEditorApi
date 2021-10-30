package fr.myriadata.myriaaccountancyeditor.api.resource.referential;

import fr.myriadata.myriaaccountancyeditor.api.service.referential.LanguageService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/languages")
public class LanguageController {

    @Inject
    LanguageService languageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> get() {
        return languageService.get();
    }

}
