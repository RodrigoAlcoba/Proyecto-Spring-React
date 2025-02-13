package com.rodrigo.alcoba.repositories;


import com.rodrigo.alcoba.model.entities.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {


}
