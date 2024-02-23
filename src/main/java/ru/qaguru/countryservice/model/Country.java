package ru.qaguru.countryservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.qaguru.countryservice.data.CountryEntity;

public record Country(
        @JsonProperty
        String countryName,
        @JsonProperty
        String countryCode
) {

    public static Country fromEntity(CountryEntity entity) {
        return new Country(
                entity.getCountryName(),
                entity.getCountryCode()
        );
    }
}
