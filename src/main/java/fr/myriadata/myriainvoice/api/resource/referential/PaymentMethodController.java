package fr.myriadata.myriainvoice.api.resource.referential;

import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;
import fr.myriadata.myriainvoice.api.service.referential.PaymentMethodService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/payment-methods")
public class PaymentMethodController {

    @Inject
    PaymentMethodService paymentMethodService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PaymentMethod> get() {
        return paymentMethodService.get();
    }

}
