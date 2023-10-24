package com.plantapp.plantapp.user_plants.service;

import com.plantapp.plantapp.user.model.User;
//import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_plants.model.UserPlants;
import com.plantapp.plantapp.user_plants.repository.UserPlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPlantsService implements IUserPlantsService {
    private final UserPlantsRepository userPlantsRepository;

    @Autowired
    public UserPlantsService(UserPlantsRepository userPlantsRepository) {
        this.userPlantsRepository = userPlantsRepository;
    }

    @Override
    public List<UserPlants> getUserPlantsByUserId(int userId) {
        return null;
    }

    @Override
    public void addPlantToUserPlantsById(int userId, int userPlantId) {

    }

    @Override
    public void removePlantFromUserPlantsById(int userPlantId) {
        Optional<UserPlants> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if (optionalUserPlant.isPresent()) {
            userPlantsRepository.deleteById(userPlantId);
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
