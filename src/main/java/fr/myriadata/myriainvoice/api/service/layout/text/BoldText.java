package fr.myriadata.myriainvoice.api.service.layout.text;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Text;

import java.io.IOException;

public class BoldText extends Text {
    public BoldText(String text) throws IOException {
        super(text);

        setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
    }
}
