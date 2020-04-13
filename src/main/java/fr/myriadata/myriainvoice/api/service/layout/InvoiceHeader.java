package fr.myriadata.myriainvoice.api.service.layout;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.layout.paragraph.AddressParagraph;
import fr.myriadata.myriainvoice.api.service.layout.paragraph.ContactParagraph;
import fr.myriadata.myriainvoice.api.service.layout.table.CustomPageTable;
import fr.myriadata.myriainvoice.api.service.layout.text.BoldText;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
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
                .add(new BoldText(String.format("Facture  %s\n", invoice.getNumber())).setFontSize(12f))
                .add(new Text(String.format("Date de facturation : %s\n" , invoice.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));

        return new CustomPageTable(2, Arrays.asList(
           new Div().add(logo),
           new Div().add(id)
        ));
    }

    private Table senderAndRecipeint(Invoice invoice) throws IOException {
        return new CustomPageTable(2, Arrays.asList(
           new Div()
               .add(new AddressParagraph(invoice.getSender().getAddress()))
               .add(new Paragraph(""))
               .add(new ContactParagraph(invoice.getSender())),
            new Div()
                .add(new Paragraph(""))
                .add(new Paragraph(new Text("A l'attention de " + invoice.getRecipient().getName())))
                .add(new AddressParagraph(invoice.getRecipient().getAddress()))
        ));
    }

}
