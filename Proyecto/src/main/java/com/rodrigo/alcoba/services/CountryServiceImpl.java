package com.rodrigo.alcoba.services;

import com.rodrigo.alcoba.model.entities.Country;
import com.rodrigo.alcoba.repositories.CountryRepository;
import com.rodrigo.alcoba.repositories.StateRepository;
import com.rodrigo.alcoba.services.interfaces.CountryService;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateRepository stateRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Country> allCountries() {
        return (List<Country>) countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Country> findCountryById(int id) {
        return countryRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> filterCountries(String name, String state) {
        return countryRepository.filterCountries(name, state);
    }


    @Override
    @Transactional
    public Country saveCountry(Country country) {
        country.setState(stateRepository.findById(1).orElseThrow());
        if (countryRepository.existsByName(country.getName())) {
            throw new RuntimeException("El pais ya existe.");
        }
        return countryRepository.save(country);
    }


    @Override
    @Transactional
    public Country activateCountry(Integer id) {
        Country country = countryRepository.findById(id).orElseThrow();
        country.setState(stateRepository.findById(1).orElseThrow());
        return countryRepository.save(country);
    }

    @Override
    @Transactional
    public Country deactivateCountry(Integer id) {
        Country country = countryRepository.findById(id).orElseThrow();
        country.setState(stateRepository.findById(2).orElseThrow());
        return countryRepository.save(country);
    }
}
