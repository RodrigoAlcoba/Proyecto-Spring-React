package com.rodrigo.alcoba.services.interfaces;

import com.rodrigo.alcoba.model.entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> allCountries();
    Optional<Country> findCountryById(int id);
    Country saveCountry(Country country);
    Country activateCountry(Country country);
    Country deactivateCountry(Country country);
    List<Country> filterCountries(String name, String state);

}
