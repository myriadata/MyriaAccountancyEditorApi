package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import fr.myriadata.myriaaccountancyeditor.api.model.party.IdentificationNumber;
import fr.myriadata.myriaaccountancyeditor.api.model.party.Provider;
import fr.myriadata.myriaaccountancyeditor.api.service.i18n.I18nService;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.format.AmountFormat;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.constant.PdfConstants;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;


public class InvoiceFooter extends Paragraph {

    public InvoiceFooter(Provider provider, Locale locale, Currency currency, PdfDocument pdfDocument, PdfPage pdfPage) throws IOException {
        setMultipliedLeading(1);
        setTextAlignment(TextAlignment.CENTER);
        setFontSize(PdfConstants.FOOTER_FONT_SIZE);

        addCompanyName(provider);
        addCompanyAddress(provider);
        addVariousInformations(provider, locale, currency);
        addPageNumber(pdfDocument, pdfPage, locale);
    }

    private void addCompanyName(Provider provider) throws IOException {
        add(new BoldText(provider.getCorporateName() +
                (Objects.nonNull(provider.getLegalStatus()) ? " " + provider.getLegalStatus() : "") +
                "\n"));
    }

    private void addCompanyAddress(Provider provider) {
        Consumer<String> addWithSpaceSeparator = s -> this.add(s + " ");

        if (Objects.nonNull(provider.getAddress())) {
            add(new Text(provider.getAddress().display(" ")));
            add("\n");
        }
    }

    private void addVariousInformations(Provider provider, Locale locale, Currency currency) {
        boolean firstElement = true;
        if (Objects.nonNull(provider.getShareCapital())) {
            add(new Text(String.format("%s : %s",
                    I18nService.getText("invoice.footer.capital", locale),
                    new AmountFormat(locale, currency).format(provider.getShareCapital()))));
            firstElement = false;
        }

        if (Objects.nonNull(provider.getVariousIdentificationNumbers())) {
            for (IdentificationNumber identificationNumbers : provider.getVariousIdentificationNumbers()) {
                if (!firstElement) {
                    add(new Text(String.format(" %s ", I18nService.getText("common.operator.separator", locale))));
                }
                firstElement = false;

                add(new Text(String.format("%s %s %s",
                        identificationNumbers.getLabel(),
                        I18nService.getText("common.operator.assignment", locale),
                        identificationNumbers.getId())));
            }
        }
    }

    private void addPageNumber(PdfDocument pdfDocument, PdfPage pdfPage, Locale locale) {
        int numberOfPages = pdfDocument.getNumberOfPages();
        if (numberOfPages > 1) {
            add(new Text("\n\n"));
            add(String.format("%s %s%s%s",
                    I18nService.getText("invoice.footer.page", locale),
                    pdfDocument.getPageNumber(pdfPage),
                    I18nService.getText("common.operator.ratio", locale),
                    numberOfPages));
        }
    }

}
