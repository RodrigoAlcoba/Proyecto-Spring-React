package com.rodrigo.alcoba.services;

import com.rodrigo.alcoba.model.entities.EquipmentType;
import com.rodrigo.alcoba.repositories.EquipmentTypeRepository;
import com.rodrigo.alcoba.repositories.StateRepository;
import com.rodrigo.alcoba.services.interfaces.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService {

    @Autowired
    private EquipmentTypeRepository equipmentTypeRepository;

    @Autowired
    private StateRepository stateRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentType> findAll() {
        return (List<EquipmentType>) equipmentTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EquipmentType> findById(int id) {
        return equipmentTypeRepository.findById(id);
    }

    @Override
    @Transactional
    public EquipmentType save(EquipmentType equipmentType) {
        if (equipmentTypeRepository.existsByName(equipmentType.getName())) {
            throw new RuntimeException("El Tipo de Equipo ya existe.");
        }
        return equipmentTypeRepository.save(equipmentType);
    }

    @Override
    @Transactional
    public Optional<EquipmentType> activate(Integer id) {
        Optional<EquipmentType> equipmentTypeOptional = equipmentTypeRepository.findById(id);
        EquipmentType equipmentType1 = null;
        if (equipmentTypeOptional.isPresent()) {
            equipmentTypeOptional.get().setState(stateRepository.findById(1).orElseThrow());
        equipmentType1 = equipmentTypeRepository.save(equipmentTypeOptional.get());
        }
        return Optional.ofNullable(equipmentType1);
    }

    @Override
    @Transactional
    public Optional<EquipmentType> deactivate(Integer id) {
        Optional<EquipmentType> equipmentTypeOptional = equipmentTypeRepository.findById(id);
        EquipmentType equipmentType1 = null;
        if (equipmentTypeOptional.isPresent()) {
            equipmentTypeOptional.get().setState(stateRepository.findById(2).orElseThrow());
            equipmentType1 = equipmentTypeRepository.save(equipmentTypeOptional.get());
        }
        return Optional.ofNullable(equipmentType1);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentType> filterEquipmentTypes(String name, String state) {
        return equipmentTypeRepository.filterEquipmentTypes(name, state);
    }

}
