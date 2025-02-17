package com.rodrigo.alcoba.controllers;

import com.rodrigo.alcoba.model.entities.Equipment;
import com.rodrigo.alcoba.services.interfaces.CountryService;
import com.rodrigo.alcoba.services.interfaces.EquipmentService;
import com.rodrigo.alcoba.services.interfaces.EquipmentTypeService;
import com.rodrigo.alcoba.services.interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CountryService countryService;


    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public List<Equipment> findAll() {
        return equipmentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Equipment> findById(@PathVariable int id) {
        return equipmentService.findById(id);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Equipment>> filterEquipments(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String equipmentType,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String provider,
            @RequestParam(required = false) String institution,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) Integer floor,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Equipment> equipments = equipmentService.filterEquipments(
                name, equipmentType, brand, model, serialNumber, country, provider, institution, sector, floor, state, startDate, endDate);

        return ResponseEntity.ok(equipments);
    }


    @PostMapping
    public Equipment save(@RequestBody Equipment equipment) {
        return equipmentService.save(equipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Equipment equipment) {
        Optional<Equipment> updatedEquipment = equipmentService.update(equipment, id);
        if (updatedEquipment.isPresent()) {
            return ResponseEntity.ok(updatedEquipment.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipo no encontrado");
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateEquipment(
            @PathVariable int id,
            @RequestParam int userId,
            @RequestParam String reason,
            @RequestParam String comments) {

        Optional<Equipment> deactivatedEquipment = equipmentService.deactivate(id, userId, reason, comments);
        if (deactivatedEquipment.isPresent()) {
            return ResponseEntity.ok(deactivatedEquipment.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipo no encontrado");
    }
}
