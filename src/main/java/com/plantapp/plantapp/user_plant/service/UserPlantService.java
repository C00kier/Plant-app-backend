package com.plantapp.plantapp.user_plant.service;

import com.plantapp.plantapp.plant.repository.PlantRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_game_progress.service.UserGameProgressService;
import com.plantapp.plantapp.user_plant.model.UserPlant;
import com.plantapp.plantapp.user_plant.repository.UserPlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserPlantService implements IUserPlantService {
    private final UserPlantRepository userPlantRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final UserGameProgressService userGameProgressService;




    @Autowired
    public UserPlantService(UserPlantRepository userPlantRepository, UserRepository userRepository, PlantRepository plantRepository, UserGameProgressService userGameProgressService) {
        this.userPlantRepository = userPlantRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
        this.userGameProgressService = userGameProgressService;

    }

    @Override
    public List<UserPlant> getUserPlantsByUserId(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        List<UserPlant> userPlants = new ArrayList<>();
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            userPlants.addAll(userPlantRepository.findAllByUser(user));
        }

        return userPlants;
    }

    @Override
    public void addPlantToUserPlants(UserPlant userPlant) {
            userPlantRepository.save(userPlant);

        }

    @Override
    @Transactional
    public void removePlantFromUserPlantsById(int userPlantId) {
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if (optionalUserPlant.isPresent()) {
            UserPlant userPlant = optionalUserPlant.get();
            userPlantRepository.delete(userPlant);
        }
    }

    @Override
    public void updateUserPlantRoomById(int userPlantId, String roomName){
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setRoom(roomName);
            userPlantRepository.save(userPlant);
        }
    }

    @Override
    public void updateUserPlantAliasById(int userPlantId, String alias) {
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setAlias(alias);
            userPlantRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastWateredByUserPlantId(int userPlantId, Date date) {
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastWatered(date);
            userPlantRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastPropagatedByUserPlantId(int userPlantId, Date date) {
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastPropagated(date);
            userPlantRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastRepottedByUserPlantId(int userPlantId, Date date) {
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastRepotted(date);
            userPlantRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastFertilizedByUserPlantId(int userPlantId, Date date) {
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastFertilized(date);
            userPlantRepository.save(userPlant);
        }
    }

    @Override
    public void updateLastPrunedByUserPlantId(int userPlantId, Date date) {
        Optional<UserPlant> optionalUserPlant = userPlantRepository.findById(userPlantId);
        if(optionalUserPlant.isPresent()){
            UserPlant userPlant = optionalUserPlant.get();
            userPlant.setLastPruned(date);
            userPlantRepository.save(userPlant);
        }
    }

}
