package fr.myriadata.myriainvoice.api.resource;

import fr.myriadata.myriainvoice.api.service.InvoiceService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/pdf")
    public Response post(@MultipartForm InvoiceMultipartBody invoiceMultipartBody) throws IOException {
        byte[] logo = invoiceMultipartBody.getLogo().readAllBytes();
        invoiceMultipartBody.getInvoice().getProvider().setLogo(logo);

        Response.ResponseBuilder response = Response.ok(invoiceService.generate(invoiceMultipartBody.getInvoice()));
        response.header("Content-Disposition", "attachment; filename=" + invoiceMultipartBody.getInvoice().getNumber() + ".pdf");
        return response.build();
    }

}
