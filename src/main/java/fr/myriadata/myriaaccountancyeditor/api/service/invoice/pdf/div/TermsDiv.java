package fr.myriadata.myriaaccountancyeditor.api.service.invoice.pdf.div;

import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.util.List;
import java.util.Objects;

public class TermsDiv extends Div {

    public TermsDiv(List<String> terms) {
        if (Objects.nonNull(terms)) {
            if (terms.size() == 1) {
                add(new Paragraph(new Text(terms.get(0))).setMultipliedLeading(1));
            } else if (terms.size() > 1) {
                com.itextpdf.layout.element.List termList = new com.itextpdf.layout.element.List();
                for (String term : terms) {
                    termList.add(term);
                }
                add(new Paragraph().add(termList).setMultipliedLeading(1));
            }
        }
    }

}
