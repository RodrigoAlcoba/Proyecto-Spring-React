package com.rodrigo.alcoba.repositories;

import com.rodrigo.alcoba.model.entities.Country;
import com.rodrigo.alcoba.model.entities.EquipmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentTypeRepository extends CrudRepository<EquipmentType, Integer> {

    @Query("SELECT e FROM EquipmentType e WHERE " +
            "(:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:state IS NULL OR LOWER(e.state.name) LIKE LOWER(CONCAT('%', :state, '%')))")
    List<Country> filterCountries(@Param("name") String name,
                                  @Param("state") String state);
}
