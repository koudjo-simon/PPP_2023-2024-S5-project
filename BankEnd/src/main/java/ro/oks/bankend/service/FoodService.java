package ro.oks.bankend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.oks.bankend.dtos.FoodDTO;
import ro.oks.bankend.exceptions.FoodNotFoodException;
import ro.oks.bankend.mappers.FoodMapper;
import ro.oks.bankend.model.Food;
import ro.oks.bankend.model.model_utils.FoodStatus;
import ro.oks.bankend.repositories.FoodRepository;
import ro.oks.bankend.service.interfacesService.FoodServiceInterface;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class FoodService implements FoodServiceInterface {

    private final FoodRepository foodRepository;
    private final VerifyEntity verifyEntity;

    public FoodService(FoodRepository foodRepository, VerifyEntity verifyEntity) {
        this.foodRepository = foodRepository;
        this.verifyEntity = verifyEntity;
    }

    @Override
    public List<FoodDTO> getFoods() {
        log.info("Food List getting");
        return foodRepository.findAll().stream()
                .map(FoodMapper::convertToFoodDTO).collect(Collectors.toList());
    }

    @Override
    public FoodDTO getFood(String foodId) throws FoodNotFoodException {
        log.info("Food with id = "+ foodId +" getting");
        Food food = verifyEntity.verifyFood(foodId);
        return FoodMapper.convertToFoodDTO(food);
    }

    @Override
    public FoodDTO addFood(FoodDTO foodDTO) {
        log.info("Adding food");
        foodDTO.setFoodId(UUID.randomUUID().toString());
        Date date = new Date();
        foodDTO.setAddDate(date);
        foodDTO.setLastModifiedDate(date);
        foodDTO.setFoodStatus(FoodStatus.CREATED);
        return FoodMapper.convertToFoodDTO(foodRepository.save(FoodMapper.convertToFood(foodDTO)));
    }

    @Override
    public FoodDTO updateFood(String foodId, FoodDTO foodDTO) {
        log.info("Updating food with id = " + foodId);
        try {
            Food food = verifyEntity.verifyFood(foodId);
            foodDTO.setAddDate(food.getAddDate());
            foodDTO.setLastModifiedDate(new Date());
            foodDTO.setFoodStatus(food.getFoodStatus());
            foodDTO.setFoodId(food.getFoodId());
            return FoodMapper.convertToFoodDTO(foodRepository.save(FoodMapper.convertToFood(foodDTO)));
        } catch (FoodNotFoodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFood(String foodId) {
        log.info("Deleting food with id = "+foodId);
        try {
            verifyEntity.verifyFood(foodId);
            foodRepository.deleteById(foodId);
        } catch (FoodNotFoodException e) {
            e.printStackTrace();
        }
    }
}
