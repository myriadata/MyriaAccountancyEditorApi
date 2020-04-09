package fr.myriadata.myriainvoice.api.model.party;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.myriadata.myriainvoice.api.model.common.Address;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Provider {

    @JsonIgnore
    private byte[] logo;

    private String corporateName;
    private Address headOfficeAddress;
    private String legalStatus;
    private BigDecimal shareCapital;
    private List<IdentificationNumber> variousIdentificationNumbers;

}
