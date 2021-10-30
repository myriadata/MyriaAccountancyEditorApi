package fr.myriadata.myriaaccountancyeditor.api.model.tax;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ValueAddedTax {

    @NotNull
    private BigDecimal baseAmount;

    @NotNull
    private BigDecimal taxAmount;

    @NotNull
    private BigDecimal includingTaxAmount;

}
