package fr.myriadata.myriaaccountancyeditor.api.resource.invoice;

import fr.myriadata.myriaaccountancyeditor.api.service.invoice.InvoiceService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Objects;

@Path("/v1/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/pdf")
    public Response post(@MultipartForm InvoiceResource invoiceMultipartBody) throws IOException {
        if (Objects.nonNull(invoiceMultipartBody.getLogo())) {
            byte[] logo = invoiceMultipartBody.getLogo().readAllBytes();
            invoiceMultipartBody.getInvoice().getProvider().setLogo(logo);
        }

        Response.ResponseBuilder response = Response.ok(invoiceService.generate(invoiceMultipartBody.getInvoice()));
        response.header("Content-Disposition", "attachment; filename=" + invoiceMultipartBody.getInvoice().getNumber() + ".pdf");
        return response.build();
    }

}
