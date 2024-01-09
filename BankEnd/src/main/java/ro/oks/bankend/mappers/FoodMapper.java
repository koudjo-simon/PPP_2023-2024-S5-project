package ro.oks.bankend.mappers;

import org.springframework.beans.BeanUtils;
import ro.oks.bankend.dtos.FoodDTO;
import ro.oks.bankend.model.Food;

public class FoodMapper {

    public static Food convertToFood(FoodDTO foodDTO){
        Food food = new Food();
        BeanUtils.copyProperties(foodDTO, food);
        return food;
    }

    public static FoodDTO convertToFoodDTO(Food food){
        FoodDTO foodDTO = new FoodDTO();
        BeanUtils.copyProperties(food, foodDTO);
        return foodDTO;
    }

}
