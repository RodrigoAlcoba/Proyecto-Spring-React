package com.rodrigo.alcoba.model.entities.pkcompuesta;



import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class EquipmentDecommissionId implements Serializable {

    @Column(name = "equipment_id")
    private Integer equipmentId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "decommission_date")
    private LocalDate decommissionDate;
}