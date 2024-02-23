package ru.qaguru.countryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qaguru.countryservice.model.Country;
import ru.qaguru.countryservice.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @PostMapping("/addCountry")
    public Country addCountry(@RequestBody Country country) {
        countryService.addCountry(country);
        return country;
    }

    @PatchMapping("/{countryCode}/changeName")
    public ResponseEntity<Object> changeName(
            @PathVariable String countryCode,
            @RequestParam String newName
    ) {
        try {
            return ResponseEntity.ok(countryService.changeName(countryCode, newName));
        } catch (IllegalStateException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(exception.getMessage());
        }

    }
}
