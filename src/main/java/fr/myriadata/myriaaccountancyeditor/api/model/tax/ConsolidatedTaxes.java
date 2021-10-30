package fr.myriadata.myriaaccountancyeditor.api.model.tax;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ConsolidatedTaxes {

    @NotNull
    @Size(min = 1)
    @Valid
    private Map<BigDecimal, ValueAddedTax> byAmount;

    @NotNull
    @Valid
    private ValueAddedTax total;

}
