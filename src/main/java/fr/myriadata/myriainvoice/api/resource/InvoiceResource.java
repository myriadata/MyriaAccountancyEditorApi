package fr.myriadata.myriainvoice.api.resource;

import fr.myriadata.myriainvoice.api.model.Invoice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/invoices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvoiceResource {

    @POST
    public Invoice post(Invoice invoice) {
        return invoice;
    }

}
