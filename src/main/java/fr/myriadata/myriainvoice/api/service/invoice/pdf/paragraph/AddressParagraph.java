package fr.myriadata.myriainvoice.api.service.invoice.pdf.paragraph;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import fr.myriadata.myriainvoice.api.model.common.Address;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.constant.PdfConstants;
import fr.myriadata.myriainvoice.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class AddressParagraph extends Paragraph {

    public AddressParagraph(Address address) throws IOException {
        setMultipliedLeading(1);
        setMarginBottom(PdfConstants.TEXT_FONT_SIZE);

        add(new BoldText(String.format("%s\n", address.getIdentification())));
        add(new Text(address.display("\n")));
    }

}
