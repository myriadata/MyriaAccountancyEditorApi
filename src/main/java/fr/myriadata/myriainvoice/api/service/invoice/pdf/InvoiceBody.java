package fr.myriadata.myriainvoice.api.service.invoice.pdf;

import com.itextpdf.layout.element.Div;
import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.div.OrderDiv;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.div.PaymentInstructionsDiv;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.div.TaxDiv;

import java.io.IOException;

public class InvoiceBody extends Div {

    public InvoiceBody(Invoice invoice) throws IOException {
        add(new OrderDiv(invoice.getOrder(), invoice.getLocale(), invoice.getCurrency()));
        add(new TaxDiv(invoice.getConsolidatedTaxes(), invoice.getLocale(), invoice.getCurrency()));
        add(new PaymentInstructionsDiv(invoice.getPaymentInstructions(), invoice.getLocale(), invoice.getCurrency()));
    }

}
