package fr.myriadata.myriainvoice.api.service.layout.format;

import java.text.DecimalFormat;

public class AmountFormat extends DecimalFormat {

    public AmountFormat() {
        super("##0.00");
    }

}
