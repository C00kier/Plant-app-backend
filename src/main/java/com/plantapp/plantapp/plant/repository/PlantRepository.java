package com.plantapp.plantapp.plant.repository;

import com.plantapp.plantapp.plant.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
    @Query("SELECT p FROM Plant p WHERE LOWER(p.botanicalName) LIKE LOWER(CONCAT('%', :plantName, '%')) OR LOWER(p.commonName) LIKE LOWER(CONCAT('%', :plantName, '%'))")
    List<Plant> findByBotanicalNameIgnoreCaseOrCommonNameIgnoreCase(String plantName);
    void deleteById(int plantId);
}
