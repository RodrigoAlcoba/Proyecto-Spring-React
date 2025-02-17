package com.rodrigo.alcoba.repositories;


import com.rodrigo.alcoba.model.entities.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Integer> {


    @Query("SELECT c FROM Country c WHERE " +
            "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:state IS NULL OR LOWER(c.state.name) LIKE LOWER(CONCAT('%', :state, '%')))")
    List<Country> filterCountries(@Param("name") String name,
                                  @Param("state") String state);

    boolean existsByName(String name);
}
