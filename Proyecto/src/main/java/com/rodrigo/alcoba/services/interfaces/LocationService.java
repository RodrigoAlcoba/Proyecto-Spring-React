package com.rodrigo.alcoba.services.interfaces;

import com.rodrigo.alcoba.model.entities.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAll();

    Optional<Location> findById(int id);
}
