package com.rodrigo.alcoba.model.entities;



import com.rodrigo.alcoba.model.entities.pkcompuesta.EquipmentDecommissionId;
import jakarta.persistence.*;


@Entity
@Table(name = "equipment_decommission")
public class EquipmentDecommission {

    @EmbeddedId
    private EquipmentDecommissionId id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", insertable = false, updatable = false)
    private Equipment equipment;

    @Column(name = "decommission_reason", nullable = false, length = 50)
    private String decommissionReason;

    @Column(name = "comments", nullable = false, length = 200)
    private String comments;

    public EquipmentDecommissionId getId() {
        return id;
    }

    public void setId(EquipmentDecommissionId id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getDecommissionReason() {
        return decommissionReason;
    }

    public void setDecommissionReason(String decommissionReason) {
        this.decommissionReason = decommissionReason;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

