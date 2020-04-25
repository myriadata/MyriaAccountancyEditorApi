package fr.myriadata.myriainvoice.api.service.invoice;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.InvoiceBody;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.InvoiceHeader;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.constant.PdfConstants;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.handler.TextFooterEventHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@ApplicationScoped
public class InvoiceService {

    public byte[] generate(@Valid Invoice invoice) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(byteArrayOutputStream));
        Document document = new Document(pdfDocument);
        document.setBottomMargin(pdfDocument.getNumberOfPages() > 1 ? 90f : 72f);
        document.setFontSize(PdfConstants.TEXT_FONT_SIZE);

        document.add(new InvoiceHeader(invoice));
        document.add(new InvoiceBody(invoice));
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler(invoice.getProvider(), invoice.getLocale(), invoice.getCurrency()));

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

}
