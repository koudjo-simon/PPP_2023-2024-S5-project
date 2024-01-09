package ro.oks.bankend.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ro.oks.bankend.dtos.CommandDTO;
import ro.oks.bankend.exceptions.CommandNotFoundException;
import ro.oks.bankend.exceptions.CustomerNotFoundException;
import ro.oks.bankend.service.interfacesService.CommandServiceInterface;
import ro.oks.bankend.web.interfacesWeb.CommandApiInterface;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CommandController implements CommandApiInterface {

    private final CommandServiceInterface commandServiceInterface;

    public CommandController(CommandServiceInterface commandServiceInterface) {
        this.commandServiceInterface = commandServiceInterface;
    }

    @Override
    public List<CommandDTO> getCommands() {
        return commandServiceInterface.getCommands();
    }

    @Override
    public CommandDTO getCommand(String commandId) throws CommandNotFoundException {
        return commandServiceInterface.getCommand(commandId);
    }

    @Override
    public CommandDTO addCommand(String customerId) throws CustomerNotFoundException {
        return commandServiceInterface.addCommand(customerId);
    }

    @Override
    public CommandDTO updateCommand(String commandId, CommandDTO commandDTO)
            throws CommandNotFoundException {
        return commandServiceInterface.updateCommand(commandId, commandDTO);
    }

    @Override
    public void deleteCommand(String commandId) throws CommandNotFoundException {
        commandServiceInterface.deleteCommand(commandId);
    }
}
