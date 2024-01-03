package com.plantapp.plantapp.plant.repository;

import com.plantapp.plantapp.plant.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
    void deleteById(int plantId);
}
