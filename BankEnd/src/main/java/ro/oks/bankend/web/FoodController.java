package ro.oks.bankend.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ro.oks.bankend.dtos.FoodDTO;
import ro.oks.bankend.exceptions.FoodNotFoodException;
import ro.oks.bankend.service.interfacesService.FoodServiceInterface;
import ro.oks.bankend.web.interfacesWeb.FoodApiInterface;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FoodController implements FoodApiInterface {

    private final FoodServiceInterface foodServiceInterface;

    public FoodController(FoodServiceInterface foodServiceInterface) {
        this.foodServiceInterface = foodServiceInterface;
    }

    @Override
    public List<FoodDTO> getFoods() {
        return foodServiceInterface.getFoods();
    }

    @Override
    public FoodDTO getFood(String foodId) throws FoodNotFoodException {
        return foodServiceInterface.getFood(foodId);
    }

    @Override
    public FoodDTO addFood(FoodDTO foodDTO) {
        return foodServiceInterface.addFood(foodDTO);
    }

    @Override
    public FoodDTO updateFood(String foodId, FoodDTO foodDTO) {
        return foodServiceInterface.updateFood(foodId, foodDTO);
    }

    @Override
    public void deleteFood(String foodId) throws FoodNotFoodException {
        foodServiceInterface.deleteFood(foodId);
    }
}
