package fr.myriadata.myriainvoice.api.model.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Contact {

    @NotNull
    @Valid
    private Address address;

    private String name;
    private String email;
    private String landlinePhoneNumber;
    private String mobilePhoneNumber;

}
