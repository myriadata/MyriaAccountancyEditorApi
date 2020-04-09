package fr.myriadata.myriainvoice.api.model.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {

    private Address address;
    private String email;
    private String landlinePhoneNumber;
    private String mobilePhoneNumber;

}
