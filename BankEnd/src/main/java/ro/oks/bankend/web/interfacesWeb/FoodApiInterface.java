package ro.oks.bankend.web.interfacesWeb;

import org.springframework.web.bind.annotation.*;
import ro.oks.bankend.dtos.FoodDTO;
import ro.oks.bankend.exceptions.FoodNotFoodException;
import ro.oks.bankend.web.interfacesWeb.utils.Constants;

import java.util.List;

public interface FoodApiInterface {

    String URL = "/foods";
    String ID = "/{foodId}";

    @GetMapping(Constants.BASE_URL + URL)
    List<FoodDTO> getFoods();

    @GetMapping(Constants.BASE_URL + URL + ID)
    FoodDTO getFood(@PathVariable String foodId) throws FoodNotFoodException;

    @PostMapping(Constants.BASE_URL + URL + "/add")
    FoodDTO addFood(@RequestBody FoodDTO foodDTO);

    @PutMapping(Constants.BASE_URL+ URL + ID + "/update")
    FoodDTO updateFood(@PathVariable String foodId, @RequestBody FoodDTO foodDTO);

    @DeleteMapping(Constants.BASE_URL+ URL + ID + "/delete")
    void deleteFood(@PathVariable String foodId) throws FoodNotFoodException;

}
