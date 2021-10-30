package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.paragraph;

import com.itextpdf.layout.element.Paragraph;

public class NullableParagraph extends Paragraph {

    public NullableParagraph(String text) {
        super();

        if (text != null) {
            add(text);
        }
    }

}
