package fr.myriadata.myriaaccountancyeditor.api.model.order;

import fr.myriadata.myriaaccountancyeditor.api.service.validator.ValidOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@ValidOrder
public class Order {

    private String number;
    private String customerReference;
    private String description;

    @Valid
    private List<OrderLine> lines;

}
