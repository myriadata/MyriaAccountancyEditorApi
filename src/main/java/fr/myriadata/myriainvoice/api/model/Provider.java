package fr.myriadata.myriainvoice.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Provider {

    private String corporateName;
    private Address headOfficeAddress;
    private String legalStatus;
    private BigDecimal shareCapital;
    private List<IdentificationNumber> variousIdentificationNumbers;

}
