package fr.myriadata.myriainvoice.api.model.party;

import fr.myriadata.myriainvoice.api.model.common.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Customer {

    private String recipient;

    @NotNull
    @Valid
    private Address address;

}
