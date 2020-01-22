package fr.myriadata.myriainvoice.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvoiceLine {

    private String description;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal variableTax;

}
