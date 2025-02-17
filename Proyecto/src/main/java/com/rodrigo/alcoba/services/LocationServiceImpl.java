package com.rodrigo.alcoba.services;

import com.rodrigo.alcoba.model.entities.Location;
import com.rodrigo.alcoba.repositories.LocationRepository;
import com.rodrigo.alcoba.services.interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Location> findAll() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(int id) {
        return locationRepository.findById(id);
    }
}

