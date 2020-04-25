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

        Consumer<String> addToParagraph = s -> add(new Text(String.format("%s\n", s)));
        Optional.ofNullable(address.getInsideBuildingInformations()).ifPresent(addToParagraph);
        Optional.ofNullable(address.getOutsideBuildingInformations()).ifPresent(addToParagraph);
        Optional.ofNullable(address.getStreet()).ifPresent(addToParagraph);
        Optional.ofNullable(address.getPostOfficeBox()).ifPresent(addToParagraph);
        Optional.of(zipCodeAndCity(address)).ifPresent(addToParagraph);
        Optional.ofNullable(address.getCountry()).ifPresent(addToParagraph);
    }

    private String zipCodeAndCity(Address address) {
        String zipCodeAndCity = "";
        if (Objects.nonNull(address.getZipCode())) {
            zipCodeAndCity = zipCodeAndCity + address.getZipCode() + " ";
        }
        if (Objects.nonNull(address.getCity())) {
            zipCodeAndCity = zipCodeAndCity + address.getCity();
        }
        return zipCodeAndCity;
    }
}
