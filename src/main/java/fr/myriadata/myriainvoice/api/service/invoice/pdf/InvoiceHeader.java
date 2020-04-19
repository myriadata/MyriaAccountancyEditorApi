package fr.myriadata.myriainvoice.api.service.invoice.pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.i18n.I18nService;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.format.DateFormat;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.paragraph.AddressParagraph;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.paragraph.ContactParagraph;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.table.FlexboxTable;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;
import java.util.Arrays;

public class InvoiceHeader extends Div {

    public InvoiceHeader(Invoice invoice) throws IOException {
        add(invoiceId(invoice));
        add(new Paragraph(""));

        add(senderAndRecipeint(invoice));
        add(new Paragraph(""));
    }

    private Table invoiceId(Invoice invoice) throws IOException {
        Image logo = new Image(ImageDataFactory.create(invoice.getProvider().getLogo()))
                .setMaxWidth(216f)
                .setMaxHeight(72f);

        Paragraph id = new Paragraph()
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new BoldText(String.format("%s %s\n",
                        I18nService.get("common.invoice", invoice.getLocale()),
                        invoice.getNumber())).setFontSize(12f))
                .add(new Text(String.format("%s : %s\n",
                        I18nService.get("invoice.header.date", invoice.getLocale()),
                        new DateFormat(invoice.getLocale()).format(invoice.getDate()))));

        return new FlexboxTable(2, Arrays.asList(
           new Div().add(logo),
           new Div().add(id)
        ));
    }

    private Table senderAndRecipeint(Invoice invoice) throws IOException {
        return new FlexboxTable(2, Arrays.asList(
           new Div()
               .add(new AddressParagraph(invoice.getSender().getAddress()))
               .add(new Paragraph(""))
               .add(new ContactParagraph(invoice.getSender(), invoice.getLocale())),
            new Div()
                .add(new Paragraph(""))
                .add(new Paragraph(new Text(String.format("%s %s",
                        I18nService.get("invoice.header.to", invoice.getLocale()),
                        invoice.getRecipient().getName()))))
                .add(new AddressParagraph(invoice.getRecipient().getAddress()))
        ));
    }

}
