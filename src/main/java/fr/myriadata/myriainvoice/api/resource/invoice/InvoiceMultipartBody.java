package fr.myriadata.myriainvoice.api.resource.invoice;

import fr.myriadata.myriainvoice.api.model.Invoice;
import lombok.Getter;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Getter
@Setter
public class InvoiceMultipartBody {

    @FormParam("logo")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream logo;

    @FormParam("invoice")
    @PartType(MediaType.APPLICATION_JSON)
    public Invoice invoice;

}
