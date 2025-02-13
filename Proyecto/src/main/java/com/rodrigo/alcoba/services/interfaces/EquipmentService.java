package com.rodrigo.alcoba.services.interfaces;

import com.rodrigo.alcoba.model.entities.Equipment;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {

    List<Equipment> findAll();

   Optional<Equipment> findById(int id);
   Equipment save(Equipment equipment);
   Optional<Equipment> update(Equipment equipment, Integer id);
   public Optional<Equipment> deactivate(int equipmentId, int userId, String reason, String comments);
   List<Equipment> filterEquipments(String name,String equipmentType, String brand, String model, String serialNumber,
                                     String country,  String provider, String institution, String sector, Integer floor, String state);
}
