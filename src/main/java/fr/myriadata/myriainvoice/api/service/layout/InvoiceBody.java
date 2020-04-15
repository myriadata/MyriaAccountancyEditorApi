package fr.myriadata.myriainvoice.api.service.layout;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.layout.div.OrderDiv;
import fr.myriadata.myriainvoice.api.service.layout.div.PaymentInstructionsDiv;
import fr.myriadata.myriainvoice.api.service.layout.div.TaxDiv;

import java.io.IOException;

public class InvoiceBody extends Div {

    public InvoiceBody(Invoice invoice) throws IOException {
        add(new OrderDiv(invoice.getOrder(), invoice.getCurrency()));
        add(new Paragraph(""));

        add(new TaxDiv(invoice.getConsolidatedTaxes(), invoice.getCurrency()));
        add(new Paragraph(""));

        add(new PaymentInstructionsDiv(invoice.getPaymentInstructions(), invoice.getCurrency()));
    }

}
