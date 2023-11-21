package com.plantapp.plantapp.user_plant.service;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import com.plantapp.plantapp.user_plant.repository.UserPlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserPlantService implements IUserPlantService {
    private final UserPlantsRepository userPlantsRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;


    @Autowired
    public UserPlantService(UserPlantsRepository userPlantsRepository, UserRepository userRepository, PlantRepository plantRepository) {
        this.userPlantsRepository = userPlantsRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
    }

    @Override
    public List<UserPlant> getUserPlantsByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<UserPlant> userPlants = new ArrayList<>();
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            userPlants.addAll(userPlantsRepository.findAllByUser(user));
        }

        return userPlants;
    }

    @Override
    public void addPlantToUserPlantsById(UserPlant userPlant) {
            userPlantsRepository.save(userPlant);
        }

    @Override
    public void removePlantFromUserPlantsById(int userPlantId) {
        Optional<UserPlant> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if (optionalUserPlant.isPresent()) {
            UserPlant userPlant = optionalUserPlant.get();
            userPlantsRepository.delete(userPlant);
        }
    }

    @Override
    public void updateUserPlantRoomById(int userPlantId, String roomName){
        Optional<UserPlant> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setRoom(roomName);
            userPlantsRepository.save(userPlant);
        }
    }

    @Override
    public void updateUserPlantAliasById(int userPlantId, String alias) {
        Optional<UserPlant> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setAlias(alias);
            userPlantsRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastWateringByUserPlantId(int userPlantId) {
        Optional<UserPlant> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastWater(new Date());
            userPlantsRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastPropagateByUserPlantId(int userPlantId) {
        Optional<UserPlant> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastPropagated(new Date());
            userPlantsRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastRepotByUserPlantId(int userPlantId) {
        Optional<UserPlant> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastRepotted(new Date());
            userPlantsRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastFertilizerByUserPlantId(int userPlantId) {
        Optional<UserPlant> optionalUserPlant = userPlantsRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastFertilizer(new Date());
            userPlantsRepository.save(userPlant);
        }
    }
}
