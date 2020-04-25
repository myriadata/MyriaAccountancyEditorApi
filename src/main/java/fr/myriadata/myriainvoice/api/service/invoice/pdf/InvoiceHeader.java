package fr.myriadata.myriainvoice.api.service.invoice.pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.i18n.I18nService;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.constant.PdfConstants;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.format.DateFormat;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.paragraph.AddressParagraph;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.paragraph.ContactParagraph;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.FlexboxTable;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class InvoiceHeader extends Div {

    public InvoiceHeader(Invoice invoice) throws IOException {
        setMarginBottom(PdfConstants.TEXT_FONT_SIZE);

        if (Objects.nonNull(invoice.getProvider().getLogo())) {
            invoiceHeaderWithLogo(invoice);
        } else {
            invoiceHeaderWithoutLogo(invoice);
        }
    }

    private void invoiceHeaderWithLogo(Invoice invoice) throws IOException {
        add(invoiceId(invoice));
        add(senderAndRecipeint(invoice));
    }

    private void invoiceHeaderWithoutLogo(Invoice invoice) throws IOException {
        add(new FlexboxTable(2, Arrays.asList(
            senderDiv(invoice),
            new Div()
                .add(idDiv(invoice).setMarginBottom(PdfConstants.TEXT_FONT_SIZE * 3))
                .add(recipientDiv(invoice))
        )));
    }

    private Table invoiceId(Invoice invoice) throws IOException {
        return new FlexboxTable(2, Arrays.asList(
            logoDiv(invoice),
            idDiv(invoice)));
    }

    private Table senderAndRecipeint(Invoice invoice) throws IOException {
        return new FlexboxTable(2, Arrays.asList(
            senderDiv(invoice),
            recipientDiv(invoice)
        ));
    }

    private Div logoDiv(Invoice invoice) {
        Div logoDiv = new Div();
        if (Objects.nonNull(invoice.getProvider().getLogo())) {
            logoDiv.add(new Image(ImageDataFactory.create(invoice.getProvider().getLogo()))
                    .setMaxWidth(216f)
                    .setMaxHeight(72f));
        }
        return logoDiv;
    }

    private Div idDiv(Invoice invoice) throws IOException {
        Div idDiv = new Div().add(
            new Paragraph()
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new BoldText(String.format("%s %s\n",
                        I18nService.get("common.invoice", invoice.getLocale()),
                        invoice.getNumber())).setFontSize(PdfConstants.TITLE_FONT_SIZE))
                .add(new Text(String.format("%s : %s\n",
                        I18nService.get("invoice.header.date", invoice.getLocale()),
                        new DateFormat(invoice.getLocale()).format(invoice.getDate()))))
        );
        return idDiv;
    }

    private Div senderDiv(Invoice invoice) throws IOException {
        return new Div()
                .add(new AddressParagraph(invoice.getSender().getAddress()))
                .add(new ContactParagraph(invoice.getSender(), invoice.getLocale()));
    }

    private Div recipientDiv(Invoice invoice) throws IOException {
        Paragraph toParagraph = new Paragraph();
        toParagraph.add(I18nService.get("invoice.header.to", invoice.getLocale()) + " ");
        if (Objects.nonNull(invoice.getRecipient().getName())) {
            toParagraph.add(invoice.getRecipient().getName());
        }

        return new Div()
                .setMarginTop(PdfConstants.TEXT_FONT_SIZE)
                .add(toParagraph)
                .add(new AddressParagraph(invoice.getRecipient().getAddress()));
    }

}
