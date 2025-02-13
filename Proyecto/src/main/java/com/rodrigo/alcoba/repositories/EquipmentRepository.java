package com.rodrigo.alcoba.repositories;

import org.springframework.data.repository.CrudRepository;
import com.rodrigo.alcoba.model.entities.Equipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {

    @Query("SELECT e FROM Equipment e WHERE " +
            "(:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:equipmentType IS NULL OR LOWER(e.equipmentType.name) LIKE LOWER(CONCAT('%', :equipmentType, '%'))) " +
            "AND (:brand IS NULL OR LOWER(e.brand) LIKE LOWER(CONCAT('%', :brand, '%'))) " +
            "AND (:model IS NULL OR LOWER(e.model) LIKE LOWER(CONCAT('%', :model, '%'))) " +
            "AND (:serialNumber IS NULL OR LOWER(e.serialNumber) LIKE LOWER(CONCAT('%', :serialNumber, '%'))) " +
            "AND (:country IS NULL OR LOWER(e.country.name) LIKE LOWER(CONCAT('%', :country, '%'))) " +
            "AND (:provider IS NULL OR LOWER(e.provider) LIKE LOWER(CONCAT('%', :provider, '%'))) " +
            "AND (:institution IS NULL OR LOWER(e.location.institution) LIKE LOWER(CONCAT('%', :institution, '%'))) " +
            "AND (:sector IS NULL OR LOWER(e.location.sector) LIKE LOWER(CONCAT('%', :sector, '%'))) " +
            "AND (:floor IS NULL OR e.location.floor = :floor) " +
            "AND (:state IS NULL OR LOWER(e.state.name) LIKE LOWER(CONCAT('%', :state, '%')))")
    List<Equipment> filterEquipments(@Param("name") String name,
                                     @Param("equipmentType") String equipmentType,
                                     @Param("brand") String brand,
                                     @Param("model") String model,
                                     @Param("serialNumber") String serialNumber,
                                     @Param("country") String country,
                                     @Param("provider") String provider,
                                     @Param("institution") String institution,
                                     @Param("sector") String sector,
                                     @Param("floor") Integer floor,
                                     @Param("state") String state);


}
