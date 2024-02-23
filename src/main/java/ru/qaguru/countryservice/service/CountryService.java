package ru.qaguru.countryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.qaguru.countryservice.data.CountryEntity;
import ru.qaguru.countryservice.data.CountryRepository;
import ru.qaguru.countryservice.model.Country;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository
                .findAll()
                .stream()
                .map(Country::fromEntity)
                .toList();
    }

    public void addCountry(Country country) {
        countryRepository.save(
                CountryEntity.fromJson(country)
        );
    }

    public Country changeName(String countryCode, String newName) {
        CountryEntity country = countryRepository.findByCountryCode(countryCode);
        if (country == null) {
            throw new IllegalStateException("Country " + countryCode + " not found");
        } else {
            country.setCountryName(newName);
            countryRepository.save(country);
            return Country.fromEntity(country);
        }
    }
}
