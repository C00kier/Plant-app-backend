package com.plantapp.plantapp.plant.service;

import com.plantapp.plantapp.plant.model.Plant;
import com.plantapp.plantapp.plant.model.PlantNameDTO;
import com.plantapp.plantapp.plant.repository.PlantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService implements IPlantService {
    private final PlantRepository plantRepository;
    private final PlantDTOMapper plantDTOMapper;

    public PlantService(PlantRepository plantRepository, PlantDTOMapper plantDTOMapper) {
        this.plantRepository = plantRepository;
        this.plantDTOMapper = plantDTOMapper;
    }

    @Override
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    @Override
    public Optional<Plant> getPlantById(int plantId) {
        return plantRepository.findById(plantId);
    }

    @Override
    @Transactional
    public List<PlantNameDTO> getPlantsByName(String plantName) {
        String likeExpression = "%" + plantName.toLowerCase() + "%";
        List<Plant> plants = plantRepository.findByBotanicalNameIgnoreCaseOrCommonNameIgnoreCase(likeExpression);
        return plantDTOMapper.getShorterPlant(plants);
    }

    @Override
    public List<Plant> getFullPlantsByName(String plantName){
        String likeExpression = "%" + plantName.toLowerCase() + "%";
        return plantRepository.findByBotanicalNameIgnoreCaseOrCommonNameIgnoreCase(likeExpression);
    }



    @Override
    public Plant addNewPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    @Override
    public void deletePlantById(int plantId) {
        plantRepository.deleteById(plantId);
    }

    @Override
    public Plant changePlantById(int plantId, Plant plant) {
        plant.setPlantId(plantId);
        return plantRepository.save(plant);
    }

    @Override
    public List<PlantNameDTO> getPlantsBySunIntensity(int sun,String plantName){
        List<Plant> plants = getFullPlantsByName(plantName);
        plants= plants.stream().
                filter(p->p.getSun()==sun&&p.getCommonName().contains(plantName))
                .toList();
        return plantDTOMapper.getShorterPlant(plants);
    }

    @Override
    public List<PlantNameDTO> getPlantsByDifficulty(int difficulty,String plantName){
        List<Plant> plants = getFullPlantsByName(plantName);
        plants = plants.stream()
                .filter(p->p.getCareDifficulty()==difficulty&&p.getCommonName().contains(plantName))
                .toList();
        return plantDTOMapper.getShorterPlant(plants);
    }

    @Override
    public List<PlantNameDTO> getAirPurifyingPlants(String plantName){
        List<Plant> plants = getFullPlantsByName(plantName);

        plants= plants.stream()
                .filter(p->p.isAirPurifying()==true&&p.getCommonName().contains(plantName))
                .toList();
        return plantDTOMapper.getShorterPlant(plants);
    }

    @Override
    public List<PlantNameDTO> getNonToxicPlants(String plantName){
        List<Plant> plants = getFullPlantsByName(plantName);
        plants= plants.stream()
                .filter(p->p.isToxicity()==false && p.getCommonName().contains(plantName))
                .toList();
        return plantDTOMapper.getShorterPlant(plants);
    }

    @Override
    public List<PlantNameDTO> getPlantsByQuizAnswers(boolean isToxic, int sun, boolean isAirPurifying, double matureSize, int careDifficulty){
        List<Plant> plants = plantRepository.findAll();
        plants = plants.stream()
                .filter(p->p.isToxicity()==isToxic && p.getSun()==sun && p.isAirPurifying()==isAirPurifying
                && p.getMatureSize()==matureSize && p.getCareDifficulty()==careDifficulty)
                .toList();
        return plantDTOMapper.getShorterPlant(plants);
    }
}
