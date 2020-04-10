package fr.myriadata.myriainvoice.api.service.layout;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.layout.div.OrderDiv;
import fr.myriadata.myriainvoice.api.service.layout.div.TaxDiv;
import fr.myriadata.myriainvoice.api.service.layout.paragraph.MultiLineParagraph;

import java.io.IOException;

public class InvoiceBody extends Div {

    public InvoiceBody(Invoice invoice) throws IOException {
        add(new OrderDiv(invoice.getOrder()));
        add(new Paragraph(""));

        add(new TaxDiv(invoice.getConsolidatedTaxes()));
        add(new Paragraph("\n"));

        add(new MultiLineParagraph(invoice.getVariousParticulars()));
    }

}
