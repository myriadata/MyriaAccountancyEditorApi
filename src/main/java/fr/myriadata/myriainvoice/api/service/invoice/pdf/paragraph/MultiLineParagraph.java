package fr.myriadata.myriainvoice.api.service.invoice.pdf.paragraph;

import com.itextpdf.layout.element.Paragraph;

import java.util.List;

public class MultiLineParagraph extends Paragraph {

    public MultiLineParagraph(List<String> lines) {
        setMultipliedLeading(1);

        for (String particular : lines) {
            add(particular + "\n");
        }
    }

}
