package com.plantapp.plantapp.user_plants.repository;

import com.plantapp.plantapp.user_plants.model.UserPlants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlantsRepository extends JpaRepository<UserPlants, Integer> {
}
