package fr.myriadata.myriainvoice.api.model.payment;

import fr.myriadata.myriainvoice.api.service.i18n.I18nService;

import java.util.Locale;

public enum PaymentMethod {

    CASH,
    CHEQUE,
    TRANSFER;

    public String getLabel(Locale locale) {
        return I18nService.getText("invoice.payment.method." + this.name().toLowerCase(), locale);
    }

}
