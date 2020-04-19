package fr.myriadata.myriainvoice.api.service.invoice.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.model.party.IdentificationNumber;
import fr.myriadata.myriainvoice.api.model.party.Provider;
import fr.myriadata.myriainvoice.api.service.i18n.I18nService;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.format.AmountFormat;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;


public class InvoiceFooter extends Paragraph {

    public InvoiceFooter(Provider provider, Locale locale, String currency, PdfDocument pdfDocument, PdfPage pdfPage) throws IOException {
        setMultipliedLeading(1);
        setTextAlignment(TextAlignment.CENTER);
        setFontSize(7f);

        addCompanyName(provider);
        addCompanyAddress(provider);
        addVariousInformations(provider, locale, currency);
        addPageNumber(pdfDocument, pdfPage, locale);
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

    private void addVariousInformations(Provider provider, Locale locale, String currency) {
        add(new Text(String.format("%s : %s",
                I18nService.get("invoice.footer.capital", locale),
                new AmountFormat(locale, currency).format(provider.getShareCapital()))));
        for (IdentificationNumber identificationNumbers : provider.getVariousIdentificationNumbers()) {
            add(new Text(String.format(" %s %s %s %s",
                    I18nService.get("common.operator.separator", locale),
                    identificationNumbers.getLabel(),
                    I18nService.get("common.operator.assignment", locale),
                    identificationNumbers.getId())));
        }
    }

    private void addPageNumber(PdfDocument pdfDocument, PdfPage pdfPage, Locale locale) {
        int numberOfPages = pdfDocument.getNumberOfPages();
        if (numberOfPages > 1) {
            add(new Text("\n\n"));
            add(String.format("%s %s%s%s",
                    I18nService.get("invoice.footer.page", locale),
                    pdfDocument.getPageNumber(pdfPage),
                    I18nService.get("common.operator.ratio", locale),
                    numberOfPages));
        }
    }

}
