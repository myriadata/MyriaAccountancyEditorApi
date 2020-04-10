package fr.myriadata.myriainvoice.api.service.layout.paragraph;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import fr.myriadata.myriainvoice.api.model.common.Address;
import fr.myriadata.myriainvoice.api.service.layout.text.BoldText;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

public class AddressParagraph extends Paragraph {

    public AddressParagraph(Address address) throws IOException {
        setMultipliedLeading(1);
        add(new BoldText(String.format("%s\n", address.getIdentification())));

        Consumer<String> addToParagraph = s -> add(new Text(String.format("%s\n", s)));
        Optional.ofNullable(address.getInsideBuildingInformations()).ifPresent(addToParagraph);
        Optional.ofNullable(address.getOutsideBuildingInformations()).ifPresent(addToParagraph);
        Optional.ofNullable(address.getStreet()).ifPresent(addToParagraph);
        Optional.ofNullable(address.getPostOfficeBox()).ifPresent(addToParagraph);
        Optional.of(address.getZipCode() + " " + address.getCity())
                .filter(s -> !s.strip().isEmpty())
                .ifPresent(addToParagraph);
        Optional.ofNullable(address.getCountry()).ifPresent(addToParagraph);
    }
}
