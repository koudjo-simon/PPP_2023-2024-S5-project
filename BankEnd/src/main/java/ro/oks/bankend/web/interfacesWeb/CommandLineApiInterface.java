package ro.oks.bankend.web.interfacesWeb;

import org.springframework.web.bind.annotation.*;
import ro.oks.bankend.dtos.CommandLineDTO;
import ro.oks.bankend.exceptions.CommandLineNotFoundException;
import ro.oks.bankend.exceptions.CommandNotFoundException;
import ro.oks.bankend.exceptions.FoodNotFoodException;
import ro.oks.bankend.web.interfacesWeb.utils.Constants;

import java.util.List;

public interface CommandLineApiInterface {

    String URL = "/commandLine";
    String ID = "/{commandLineId}";

    @GetMapping(Constants.BASE_URL + URL + "get/{commandId}/commandLine")
    List<CommandLineDTO> getCommandLines(@PathVariable String commandId) throws CommandNotFoundException;

    @GetMapping(Constants.BASE_URL + URL + "/add")
    CommandLineDTO addCommandLine(@RequestParam(name = "foodId") String foodId,
                                          @RequestParam(name = "commandId") String commandId,
                                          @RequestParam(name = "quantity") double quantity)
            throws FoodNotFoodException, CommandNotFoundException;

    @PutMapping(Constants.BASE_URL + URL + ID + "/update")
    CommandLineDTO updateCommandLine(@PathVariable String commandLineId,
                                     @RequestBody CommandLineDTO commandLineDTO)
            throws CommandLineNotFoundException;

    @DeleteMapping(Constants.BASE_URL + URL + ID + "/delete")
    void deleteCommandLine(@PathVariable String commandLineId) throws CommandLineNotFoundException;

}
