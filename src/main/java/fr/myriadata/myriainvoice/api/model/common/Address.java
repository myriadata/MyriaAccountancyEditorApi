package fr.myriadata.myriainvoice.api.model.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

@Getter
@Setter
public class Address {

    @NotBlank
    private String identification;

    private String insideBuildingInformations; // apt, stairs, ... or natural recipient or service if legal person address
    private String outsideBuildingInformations; // building, entrance, ...
    private String street;
    private String postOfficeBox;
    private String zipCode;
    private String city; // with CEDEX if needed
    private String country;

    public String display(String separator) {
        StringBuffer stringBuilder = new StringBuffer();
        Consumer<String> append = s -> stringBuilder.append(s + separator);
        Optional.ofNullable(insideBuildingInformations).ifPresent(append);
        Optional.ofNullable(outsideBuildingInformations).ifPresent(append);
        Optional.ofNullable(street).ifPresent(append);
        Optional.ofNullable(postOfficeBox).ifPresent(append);
        Optional.of(zipCodeAndCity()).ifPresent(append);
        Optional.ofNullable(country).ifPresent(append);
        return stringBuilder.toString();
    }

    private String zipCodeAndCity() {
        String zipCodeAndCity = "";
        if (Objects.nonNull(zipCode)) {
            zipCodeAndCity = zipCodeAndCity + zipCode + " ";
        }
        if (Objects.nonNull(city)) {
            zipCodeAndCity = zipCodeAndCity + city;
        }
        return zipCodeAndCity;
    }
}
