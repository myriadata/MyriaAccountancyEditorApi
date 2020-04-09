package fr.myriadata.myriainvoice.api.model.tax;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ValueAddedTax {

    private BigDecimal baseAmount;
    private BigDecimal taxAmount;
    private BigDecimal includingTaxAmount;

}
