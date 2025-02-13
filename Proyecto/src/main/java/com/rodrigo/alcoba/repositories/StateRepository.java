package com.rodrigo.alcoba.repositories;

import com.rodrigo.alcoba.model.entities.State;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StateRepository extends CrudRepository<State, Integer> {

    Optional<State> findById(int id);
}
