package fr.myriadata.myriainvoice.api.service.referential;

import fr.myriadata.myriainvoice.api.model.payment.PaymentMethod;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class PaymentMethodService {

    public List<PaymentMethod> get() {
        return Arrays.asList(PaymentMethod.values());
    }

}
