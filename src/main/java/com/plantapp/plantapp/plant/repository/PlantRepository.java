package com.plantapp.plantapp.plant.repository;

import com.plantapp.plantapp.plant.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
    List<Plant> findByBotanicalNameLikeOrCommonNameLike(String botanicalName, String commonName);
    List<Plant> findByToxicityTrue();

    List<Plant> findByToxicityFalse();

    Optional<Plant> deleteById(int plantId);
}
