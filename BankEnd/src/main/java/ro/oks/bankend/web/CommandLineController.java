package ro.oks.bankend.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ro.oks.bankend.dtos.CommandLineDTO;
import ro.oks.bankend.exceptions.CommandLineNotFoundException;
import ro.oks.bankend.exceptions.CommandNotFoundException;
import ro.oks.bankend.exceptions.FoodNotFoodException;
import ro.oks.bankend.service.interfacesService.CommandLineServiceInterface;
import ro.oks.bankend.web.interfacesWeb.CommandLineApiInterface;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CommandLineController implements CommandLineApiInterface {

    private final CommandLineServiceInterface commandLineServiceInterface;

    @Override
    public List<CommandLineDTO> getCommandLines(String commandId) throws CommandNotFoundException {
        return this.commandLineServiceInterface.getCommandLines(commandId);
    }

    public CommandLineController(CommandLineServiceInterface commandLineServiceInterface) {
        this.commandLineServiceInterface = commandLineServiceInterface;
    }

    @Override
    public CommandLineDTO addCommandLine(String foodId, String commandId, double quantity)
            throws FoodNotFoodException, CommandNotFoundException {
        return commandLineServiceInterface.addCommandLine(foodId, commandId, quantity);
    }

    @Override
    public CommandLineDTO updateCommandLine(String commandLineId, CommandLineDTO commandLineDTO)
            throws CommandLineNotFoundException {
        return commandLineServiceInterface.updateCommandLine(commandLineId, commandLineDTO);
    }

    @Override
    public void deleteCommandLine(String commandLineId) throws CommandLineNotFoundException {
        commandLineServiceInterface.deleteCommandLine(commandLineId);
    }
}
