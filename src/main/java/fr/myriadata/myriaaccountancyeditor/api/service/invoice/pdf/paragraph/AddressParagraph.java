package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.paragraph;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.constant.PdfConstants;
import fr.myriadata.myriaaccountancyeditor.api.model.common.Address;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;

public class AddressParagraph extends Paragraph {

    public AddressParagraph(Address address) throws IOException {
        setMultipliedLeading(1);
        setMarginBottom(PdfConstants.TEXT_FONT_SIZE);

        add(new BoldText(String.format("%s\n", address.getIdentification())));
        add(new Text(address.display("\n")));
    }

}
