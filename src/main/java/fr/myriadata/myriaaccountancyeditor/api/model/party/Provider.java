package fr.myriadata.myriaaccountancyeditor.api.model.party;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.myriadata.myriaaccountancyeditor.api.model.common.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class Provider {

    @JsonIgnore
    private byte[] logo;

    @NotBlank
    private String corporateName;

    @NotNull
    @Valid
    private Address address;

    private String legalStatus;

    @Valid
    private ShareCapital shareCapital;

    @Valid
    private List<IdentificationNumber> variousIdentificationNumbers;

    private Contact contact;
}
