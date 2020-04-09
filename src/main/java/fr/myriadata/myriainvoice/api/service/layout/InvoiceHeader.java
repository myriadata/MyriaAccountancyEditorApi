package fr.myriadata.myriainvoice.api.service.layout;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import fr.myriadata.myriainvoice.api.model.Invoice;
import fr.myriadata.myriainvoice.api.service.layout.text.BoldText;
import fr.myriadata.myriainvoice.api.service.layout.text.ContactParagraph;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class InvoiceHeader extends Div {

    public InvoiceHeader(Invoice invoice) throws IOException {
        add(invoiceId(invoice));
        add(new Paragraph(""));

        add(senderAndRecipeint(invoice));
        add(new Paragraph("\n"));
    }

    private Table invoiceId(Invoice invoice) throws IOException {
        Image logo = new Image(ImageDataFactory.create(invoice.getProvider().getLogo()))
                .setMaxWidth(216f)
                .setMaxHeight(72f);

        Paragraph id = new Paragraph()
                .setTextAlignment(TextAlignment.RIGHT)
                .add(new BoldText(String.format("Facture  %s\n", invoice.getNumber())).setFontSize(12f))
                .add(new Text(String.format("Date de facturation : %s\n" , invoice.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));

        Table table = new Table(new UnitValue[]{new UnitValue(UnitValue.PERCENT, 50), new UnitValue(UnitValue.PERCENT, 50)});
        table.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(logo));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(id));
        return table;
    }

    private Table senderAndRecipeint(Invoice invoice) throws IOException {
        Table table = new Table(new UnitValue[]{new UnitValue(UnitValue.PERCENT, 50), new UnitValue(UnitValue.PERCENT, 50)});
        table.setWidth(new UnitValue(UnitValue.PERCENT, 100));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(new ContactParagraph(invoice.getSender())));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(new ContactParagraph(invoice.getRecipient())));
        return table;
    }

}
