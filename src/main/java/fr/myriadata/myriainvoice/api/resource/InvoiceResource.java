package fr.myriadata.myriainvoice.api.resource;

import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.InvoiceService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/v1/invoices")
public class InvoiceResource {

    private InvoiceService invoiceService;

    InvoiceResource(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response post(Invoice invoice) throws IOException {
        Response.ResponseBuilder response = Response.ok(invoiceService.generate(invoice));
        response.header("Content-Disposition", "attachment; filename=" + invoice.getNumber() + ".pdf");
        return response.build();
    }

}
