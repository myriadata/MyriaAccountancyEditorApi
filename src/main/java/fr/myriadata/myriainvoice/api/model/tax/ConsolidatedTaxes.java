package fr.myriadata.myriainvoice.api.model.tax;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ConsolidatedTaxes {

    private Map<BigDecimal, ValueAddedTax> byAmount;
    private ValueAddedTax total;

}
