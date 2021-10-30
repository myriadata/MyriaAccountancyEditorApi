package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.paragraph;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import fr.myriadata.myriaaccountancyeditor.api.model.party.Contact;
import fr.myriadata.myriaaccountancyeditor.api.service.i18n.I18nService;
import fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.text.BoldText;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class ContactParagraph extends Paragraph {

    public ContactParagraph(Contact contact, Locale locale) throws IOException {
        setMultipliedLeading(1);

        if (Objects.nonNull(contact)) {
            if (Objects.nonNull(contact.getName()) ||
                    Objects.nonNull(contact.getEmail()) ||
                    Objects.nonNull(contact.getLandlinePhoneNumber()) ||
                    Objects.nonNull(contact.getMobilePhoneNumber())) {
                add(new BoldText(String.format("%s %s \n",
                        I18nService.getText("invoice.header.contact", locale),
                        I18nService.getText("common.operator.assignment", locale))));
            }

            Consumer<String> addToParagraph = s -> add(new Text(String.format("%s\n", s)));
            Optional.ofNullable(contact.getName()).ifPresent(addToParagraph);
            Optional.ofNullable(contact.getEmail()).ifPresent(s -> addToParagraph.accept(
                    String.format("%s %s %s",
                            I18nService.getText("common.email", locale),
                            I18nService.getText("common.operator.assignment", locale),
                            s)));
            Optional.ofNullable(contact.getLandlinePhoneNumber()).ifPresent(s -> addToParagraph.accept(
                    String.format("%s %s %s",
                            I18nService.getText("common.phone", locale),
                            I18nService.getText("common.operator.assignment", locale),
                            s)));
            Optional.ofNullable(contact.getMobilePhoneNumber()).ifPresent(s -> addToParagraph.accept(
                    String.format("%s %s %s",
                            I18nService.getText("common.mobile", locale),
                            I18nService.getText("common.operator.assignment", locale),
                            s)));
        }

    }

}
