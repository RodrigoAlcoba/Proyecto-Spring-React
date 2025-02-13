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

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDecommissionDate() {
        return decommissionDate;
    }

    public void setDecommissionDate(LocalDate decommissionDate) {
        this.decommissionDate = decommissionDate;
    }
}