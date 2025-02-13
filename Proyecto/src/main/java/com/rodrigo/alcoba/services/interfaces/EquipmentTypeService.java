package com.rodrigo.alcoba.services.interfaces;

import com.rodrigo.alcoba.model.entities.EquipmentType;

import java.util.List;
import java.util.Optional;

public interface EquipmentTypeService {

    List<EquipmentType> findAll();
    Optional<EquipmentType> findById(int id);
    EquipmentType save(EquipmentType equipmentType);
    Optional<EquipmentType> activate(Integer id);
    Optional<EquipmentType> deactivate(Integer id);
    List<EquipmentType> filterEquipmentTypes(String name, String state);

}
