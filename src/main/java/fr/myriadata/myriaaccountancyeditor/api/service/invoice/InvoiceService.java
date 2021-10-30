package fr.myriadata.myriaaccountancyeditor.api.service.invoice;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import fr.myriadata.myriaaccountancyeditor.api.model.Invoice;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.handler.TextFooterEventHandler;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.InvoiceBody;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.InvoiceHeader;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.constant.PdfConstants;

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
        document.setBottomMargin(63f);
        document.setFontSize(PdfConstants.TEXT_FONT_SIZE);

        document.add(new InvoiceHeader(invoice));
        document.add(new InvoiceBody(invoice));
        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new TextFooterEventHandler(invoice.getProvider(), invoice.getLocale(), invoice.getCurrency()));

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

}
