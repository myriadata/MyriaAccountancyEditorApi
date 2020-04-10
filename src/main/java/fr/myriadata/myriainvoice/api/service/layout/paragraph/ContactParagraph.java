package fr.myriadata.myriainvoice.api.service.layout.paragraph;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import fr.myriadata.myriainvoice.api.model.common.Contact;
import fr.myriadata.myriainvoice.api.service.layout.text.BoldText;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

public class ContactParagraph extends Paragraph {

    public ContactParagraph(Contact contact) throws IOException {
        setMultipliedLeading(1);

        Consumer<String> addToParagraph = s -> add(new Text(String.format("%s\n", s)));
        add(new BoldText(String.format("Votre contact : \n")));
        Optional.ofNullable(contact.getName()).ifPresent(addToParagraph);
        Optional.ofNullable(contact.getEmail()).ifPresent(s -> addToParagraph.accept("Email : " + s));
        Optional.ofNullable(contact.getLandlinePhoneNumber()).ifPresent(s -> addToParagraph.accept("Téléphone : " + s));
        Optional.ofNullable(contact.getMobilePhoneNumber()).ifPresent(s -> addToParagraph.accept("Mobile : " + s));
    }

}
