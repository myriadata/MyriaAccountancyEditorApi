package fr.myriadata.myriainvoice.api.service.layout;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.model.party.IdentificationNumber;
import fr.myriadata.myriainvoice.api.model.party.Provider;
import fr.myriadata.myriainvoice.api.service.layout.format.AmountFormat;
import fr.myriadata.myriainvoice.api.service.layout.text.BoldText;

import java.io.IOException;
import java.util.Optional;


public class InvoiceFooter extends Paragraph {

    public InvoiceFooter(Provider provider, String currency, PdfDocument pdfDocument, PdfPage pdfPage) throws IOException {
        setMultipliedLeading(1);
        setTextAlignment(TextAlignment.CENTER);
        setFontSize(7f);

        addCompanyName(provider);
        addCompanyAddress(provider);
        addVariousInformations(provider, currency);
        addPageNumber(pdfDocument, pdfPage);
    }

    private void addCompanyName(Provider provider) throws IOException {
        add(new BoldText(provider.getCorporateName() + " " + provider.getLegalStatus()));
        add("\n");
    }

    private void addCompanyAddress(Provider provider) {
        Optional.ofNullable(provider.getHeadOfficeAddress().getInsideBuildingInformations()).ifPresent(this::add);
        Optional.ofNullable(provider.getHeadOfficeAddress().getOutsideBuildingInformations()).ifPresent(this::add);
        Optional.ofNullable(provider.getHeadOfficeAddress().getStreet()).ifPresent(this::add);
        Optional.ofNullable(provider.getHeadOfficeAddress().getPostOfficeBox()).ifPresent(this::add);
        Optional.of(provider.getHeadOfficeAddress().getZipCode() + " " + provider.getHeadOfficeAddress().getCity())
                .filter(s -> !s.strip().isEmpty())
                .ifPresent(this::add);
        Optional.ofNullable(provider.getHeadOfficeAddress().getCountry()).ifPresent(this::add);
        add("\n");
    }

    private void addVariousInformations(Provider provider, String currency) {
        add(new Text("Capital social : " + new AmountFormat(currency).format(provider.getShareCapital())));
        for (IdentificationNumber identificationNumbers : provider.getVariousIdentificationNumbers()) {
            add(new Text(" - " + identificationNumbers.getLabel() + " : " + identificationNumbers.getId()));
        }
    }

    private void addPageNumber(PdfDocument pdfDocument, PdfPage pdfPage) {
        int numberOfPages = pdfDocument.getNumberOfPages();
        if (numberOfPages > 1) {
            add(new Text("\n\n"));
            add("page " + pdfDocument.getPageNumber(pdfPage) + "/" + numberOfPages);
        }
    }

}
