package fr.myriadata.myriainvoice.api.service.layout.text;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import fr.myriadata.myriainvoice.api.model.common.Contact;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

public class ContactParagraph extends Paragraph {

    public ContactParagraph(Contact contact) throws IOException {
        setMultipliedLeading(1);
        add(new BoldText(String.format("%s\n", contact.getAddress().getIdentification())));

        Consumer<String> addToParagraph = s -> add(new Text(String.format("%s\n", s)));
        Optional.ofNullable(contact.getAddress().getInsideBuildingInformations()).ifPresent(addToParagraph);
        Optional.ofNullable(contact.getAddress().getOutsideBuildingInformations()).ifPresent(addToParagraph);
        Optional.ofNullable(contact.getAddress().getStreet()).ifPresent(addToParagraph);
        Optional.ofNullable(contact.getAddress().getPostOfficeBox()).ifPresent(addToParagraph);
        Optional.of(contact.getAddress().getZipCode() + " " + contact.getAddress().getCity())
                .filter(s -> !s.strip().isEmpty())
                .ifPresent(addToParagraph);
        Optional.ofNullable(contact.getAddress().getCountry()).ifPresent(addToParagraph);

        Optional.ofNullable(contact.getEmail()).ifPresent(s -> addToParagraph.accept("Email : " + s));
        Optional.ofNullable(contact.getLandlinePhoneNumber()).ifPresent(s -> addToParagraph.accept("Téléphone : " + s));
        Optional.ofNullable(contact.getMobilePhoneNumber()).ifPresent(s -> addToParagraph.accept("Mobile : " + s));
    }

}
