package fr.myriadata.myriainvoice.api.model.party;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IdentificationNumber {

    @NotBlank
    private String label;

    @NotBlank
    private String id;

}
