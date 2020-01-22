package fr.myriadata.myriainvoice.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String identification;
    private String insideBuildingInformations; // apt, stairs, ... or natural recipient or service if legal person address
    private String outsideBuildingInformations; // building, entrance, ...
    private String street;
    private String postOfficeBox;
    private String zipCode;
    private String city; // with CEDEX if needed
    private String country;

}
