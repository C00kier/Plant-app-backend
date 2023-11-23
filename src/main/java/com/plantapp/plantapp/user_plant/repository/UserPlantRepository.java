package com.plantapp.plantapp.user_plant.repository;

import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlantRepository extends JpaRepository<UserPlant, Integer> {
    List<UserPlant> findAllByUser(User user);
}
