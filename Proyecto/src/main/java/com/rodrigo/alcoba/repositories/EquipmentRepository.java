package com.rodrigo.alcoba.repositories;

import org.springframework.data.repository.CrudRepository;
import com.rodrigo.alcoba.model.entities.Equipment;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {
}
