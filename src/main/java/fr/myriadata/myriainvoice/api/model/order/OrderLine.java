package fr.myriadata.myriainvoice.api.model.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderLine {

    @NotBlank
    private String description;

    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal amount;

}
