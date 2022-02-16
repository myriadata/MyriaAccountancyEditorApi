package fr.myriadata.myriaaccountancyeditor.api.model.party;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
public class ShareCapital {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Currency currency;

}
