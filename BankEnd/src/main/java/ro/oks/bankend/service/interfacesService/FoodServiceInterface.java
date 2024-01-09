package ro.oks.bankend.service.interfacesService;

import ro.oks.bankend.dtos.FoodDTO;
import ro.oks.bankend.exceptions.FoodNotFoodException;
import ro.oks.bankend.model.Food;

import java.util.List;

public interface FoodServiceInterface {

    List<FoodDTO> getFoods();
    FoodDTO getFood(String foodId) throws FoodNotFoodException;
    FoodDTO addFood(FoodDTO foodDTO);
    FoodDTO updateFood(String foodId, FoodDTO foodDTO);
    void deleteFood(String foodId) throws FoodNotFoodException;
}
