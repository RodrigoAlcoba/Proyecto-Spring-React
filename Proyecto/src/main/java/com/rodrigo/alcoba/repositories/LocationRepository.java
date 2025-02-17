package com.rodrigo.alcoba.repositories;

import com.rodrigo.alcoba.model.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {
}
