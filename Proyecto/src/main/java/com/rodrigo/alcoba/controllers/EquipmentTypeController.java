package com.rodrigo.alcoba.controllers;

import com.rodrigo.alcoba.model.entities.EquipmentType;
import com.rodrigo.alcoba.services.interfaces.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/types")
public class EquipmentTypeController {

    @Autowired
    private EquipmentTypeService equipmentTypeService;

    @GetMapping
    public List<EquipmentType> getAllTypes() {
        return equipmentTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getTypeById(@PathVariable Integer id) {
        Optional<EquipmentType> equipmentType = equipmentTypeService.findById(id);
        if (equipmentType.isPresent()) {
            return new ResponseEntity<>(equipmentType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addType(@RequestBody EquipmentType type) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentTypeService.save(type));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activate(@PathVariable Integer id) {
        Optional<EquipmentType> equipmentType = equipmentTypeService.findById(id);
        if (equipmentType.isPresent()) {
            equipmentTypeService.activate(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipmentType.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivate(@PathVariable Integer id) {
        Optional<EquipmentType> equipmentType = equipmentTypeService.findById(id);
        if (equipmentType.isPresent()) {
            equipmentTypeService.deactivate(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipmentType.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EquipmentType>> filterEquipmentTypes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String state) {

        List<EquipmentType> equipmentTypes = equipmentTypeService.filterEquipmentTypes(name, state);

        if (equipmentTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipmentTypes);
    }




}
