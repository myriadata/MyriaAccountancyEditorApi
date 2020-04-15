package fr.myriadata.myriainvoice.api.service.layout.format;

import java.text.DecimalFormat;

public class AmountFormat extends DecimalFormat {

    public AmountFormat(String currency) {
        super("##0.00" + (currency != null ? " " + currency : ""));
    }

}
