package fr.myriadata.myriaaccountancyeditor.api.model.payment;

import fr.myriadata.myriaaccountancyeditor.api.service.i18n.I18nService;

import java.util.Locale;

public enum PaymentMethod {

    CASH,
    CHEQUE,
    TRANSFER;

    public String getLabel(Locale locale) {
        return I18nService.getText("invoice.payment.method." + this.name().toLowerCase(), locale);
    }

}
