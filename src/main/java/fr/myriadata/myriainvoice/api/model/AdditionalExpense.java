package fr.myriadata.myriainvoice.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdditionalExpense {

    private String label;
    private BigDecimal amount;

}