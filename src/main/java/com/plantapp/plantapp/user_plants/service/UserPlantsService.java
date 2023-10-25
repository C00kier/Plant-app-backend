package com.plantapp.plantapp.user_plants.service;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_plants.model.UserPlants;
import com.plantapp.plantapp.user_plants.repository.UserPlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserPlantsService implements IUserPlantsService {
    private final UserPlantsRepository userPlantsRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;

    @Autowired
    public UserPlantsService(UserPlantsRepository userPlantsRepository, UserRepository userRepository, PlantRepository plantRepository) {
        this.userPlantsRepository = userPlantsRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
    }

    @Override
    public List<UserPlants> getUserPlantsByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<UserPlants> userPlants = new ArrayList<>();
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            userPlants.addAll(userPlantsRepository.findAllByUser(user));
        }

        return userPlants;
    }

    @Override
    public void addPlantToUserPlantsById(int userId, int plantId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Plant> optionalPlant = plantRepository.findById(plantId);
        if(optionalUser.isPresent() && optionalPlant.isPresent()){
            User user = optionalUser.get();
            Plant plant = optionalPlant.get();
            UserPlants userPlant = new UserPlants(user,plant, ZonedDateTime.now(ZoneId.of("CET")));
            userPlantsRepository.save(userPlant);
        }
    }

    @Override
    public void removePlantFromUserPlantsById(int userPlantId) {
        Optional<UserPlants> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if (optionalUserPlant.isPresent()) {
            UserPlants userPlant = optionalUserPlant.get();
            userPlantsRepository.delete(userPlant);
        }
    }

    @Override
    public void updateUserPlantRoomById(int userPlantId, String roomName){
        Optional<UserPlants> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlants userPlant = optionalUserPlant.get();
            userPlant.setRoom(roomName);
            userPlantsRepository.save(userPlant);
        }
    }

    @Override
    public void updateUserPlantAliasById(int userPlantId, String alias) {
        Optional<UserPlants> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlants userPlant = optionalUserPlant.get();
            userPlant.setAlias(alias);
            userPlantsRepository.save(userPlant);
        }
    }
}
