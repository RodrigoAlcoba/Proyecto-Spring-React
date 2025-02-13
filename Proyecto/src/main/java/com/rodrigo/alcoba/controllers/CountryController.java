package com.rodrigo.alcoba.controllers;

import com.rodrigo.alcoba.model.entities.Country;
import com.rodrigo.alcoba.services.interfaces.CountryService;
import com.rodrigo.alcoba.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries(){
        return countryService.allCountries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable int id){
        Optional countryOptional = countryService.findCountryById(id);
        if(countryOptional.isPresent()) {
            return ResponseEntity.ok(countryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody Country country){
       return ResponseEntity.status(HttpStatus.CREATED).body(countryService.saveCountry(country));
    }

    @PutMapping("activate/{id}")
    public ResponseEntity<?> activateCountry(@PathVariable int id){
        Optional<Country> countryOptional = countryService.findCountryById(id);
        if(countryOptional.isPresent()) {
            countryService.activateCountry(countryOptional.get().getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(countryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("deactivate/{id}")
    public ResponseEntity<?> desactivateCountry(@PathVariable int id){
        Optional<Country> countryOptional = countryService.findCountryById(id);
        if(countryOptional.isPresent()) {
            countryService.deactivateCountry(countryOptional.get().getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(countryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Country>> filterCountries(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String state) {

        List<Country> countries = countryService.filterCountries(name, state);

        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countries);
    }

}
