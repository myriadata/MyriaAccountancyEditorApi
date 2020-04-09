package fr.myriadata.myriainvoice.api.service.layout.handler;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import fr.myriadata.myriainvoice.api.model.party.Provider;
import fr.myriadata.myriainvoice.api.service.layout.InvoiceFooter;
import lombok.SneakyThrows;

public class TextFooterEventHandler implements IEventHandler {

    private Provider provider;

    public TextFooterEventHandler(Provider provider) {
        this.provider = provider;
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
        canvas.add(new InvoiceFooter(provider, pdfDocument, pdfPage));
    }

}
