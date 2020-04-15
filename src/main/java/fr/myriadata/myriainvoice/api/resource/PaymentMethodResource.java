package fr.myriadata.myriainvoice.api.resource;

import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/v1/payment-methods")
public class PaymentMethodResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PaymentMethod> get() {
        return Arrays.asList(PaymentMethod.values());
    }

}
