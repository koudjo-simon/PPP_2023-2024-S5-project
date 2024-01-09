package ro.oks.bankend.service.interfacesService;

import ro.oks.bankend.dtos.CommandDTO;
import ro.oks.bankend.exceptions.CommandNotFoundException;
import ro.oks.bankend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CommandServiceInterface {
    List<CommandDTO> getCommands();
    CommandDTO getCommand(String commandId) throws CommandNotFoundException;
    CommandDTO addCommand(String customerId) throws CustomerNotFoundException;
    CommandDTO updateCommand(String commandId, CommandDTO commandDTO) throws CommandNotFoundException;
    void deleteCommand(String commandId) throws CommandNotFoundException;
}
