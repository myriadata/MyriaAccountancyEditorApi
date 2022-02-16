package fr.myriadata.myriaaccountancyeditor.api.model.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderLine {

    @NotBlank
    private String description;

    private String unit;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal amount;

}
