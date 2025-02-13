package com.rodrigo.alcoba.services;

import com.rodrigo.alcoba.model.entities.Equipment;
import com.rodrigo.alcoba.model.entities.EquipmentDecommission;
import com.rodrigo.alcoba.model.entities.pkcompuesta.EquipmentDecommissionId;
import com.rodrigo.alcoba.repositories.EquipmentDecommissionRepository;
import com.rodrigo.alcoba.repositories.EquipmentRepository;
import com.rodrigo.alcoba.repositories.StateRepository;
import com.rodrigo.alcoba.services.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentDecommissionRepository decommissionRepository;

    @Autowired
    private StateRepository stateRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Equipment> findAll() {
        return (List<Equipment>) equipmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Equipment> findById(int id) {
        return equipmentRepository.findById(id);
    }

    @Override
    @Transactional
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    @Transactional
    public Optional<Equipment> update(Equipment equipment, Integer id) {
        Optional<Equipment> equipment1 = equipmentRepository.findById(id);
        Equipment equipment2 = null;
        if (equipment1.isPresent()) {
            equipment2 = equipment1.get();
            equipment2.setName(equipment.getName());
            equipment2.setEquipmentType(equipment.getEquipmentType());
            equipment2.setBrand(equipment.getBrand());
            equipment2.setModel(equipment.getModel());
            equipment2.setSerialNumber(equipment.getSerialNumber());
            equipment2.setCountry(equipment.getCountry());
            equipment2.setProvider(equipment.getProvider());
            equipment2.setAcquisitionDate(equipment.getAcquisitionDate());
            equipment2.setLocation(equipment.getLocation());
            equipment2.setImage(equipment.getImage());
            equipmentRepository.save(equipment2);
        }
        return Optional.ofNullable(equipment);
    }

    @Override
    @Transactional
    public Optional<Equipment> deactivate(int equipmentId, int userId, String reason, String comments) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentId);
        if (equipmentOptional.isPresent()) {
            Equipment equipment = equipmentOptional.get();

            equipment.setState(stateRepository.findById(2).orElseThrow());


            EquipmentDecommissionId decommissionId = new EquipmentDecommissionId();
            decommissionId.setEquipmentId(equipmentId);
            decommissionId.setUserId(userId);
            decommissionId.setDecommissionDate(LocalDate.now());

            EquipmentDecommission decommission = new EquipmentDecommission();
            decommission.setId(decommissionId);
            decommission.setEquipment(equipment);
            decommission.setDecommissionReason(reason);
            decommission.setComments(comments);

            decommissionRepository.save(decommission);
            return Optional.of(equipmentRepository.save(equipment));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipment> filterEquipments(String name, String equipmentType, String brand, String model, String serialNumber, String country, String provider, String institution, String sector, Integer floor, String state) {
        return equipmentRepository.filterEquipments(name, equipmentType, brand, model, serialNumber, country, provider, institution, sector, floor, state);
    }
}
