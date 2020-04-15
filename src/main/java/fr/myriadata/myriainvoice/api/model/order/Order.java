package fr.myriadata.myriainvoice.api.model.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {

    private String number;
    private String customerReference;
    private String description;
    private List<InvoiceLine> lines;

}
