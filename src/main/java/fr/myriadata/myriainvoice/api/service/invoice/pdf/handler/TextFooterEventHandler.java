package fr.myriadata.myriainvoice.api.service.invoice.pdf.handler;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import fr.myriadata.myriainvoice.api.model.party.Provider;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.InvoiceFooter;
import lombok.SneakyThrows;

import java.util.Locale;

public class TextFooterEventHandler implements IEventHandler {

    private Provider provider;
    private Locale locale;
    private String currency;

    public TextFooterEventHandler(Provider provider, Locale locale, String currency) {
        this.provider = provider;
        this.locale = locale;
        this.currency = currency;
    }

    @SneakyThrows
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent pdfDocumentEvent = (PdfDocumentEvent) event;
        Rectangle pageSize = pdfDocumentEvent.getPage().getPageSize();
        PdfDocument pdfDocument = pdfDocumentEvent.getDocument();
        PdfPage pdfPage = pdfDocumentEvent.getPage();
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);

        pageSize.setY(pdfDocument.getNumberOfPages() > 1 ? -775 : -780);
        Canvas canvas = new Canvas(pdfCanvas, pdfDocument, pageSize);
        canvas.add(new InvoiceFooter(provider, locale, currency, pdfDocument, pdfPage));
    }

}
