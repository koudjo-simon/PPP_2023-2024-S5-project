package ro.oks.bankend.web.interfacesWeb;

import org.springframework.web.bind.annotation.*;
import ro.oks.bankend.dtos.CommandDTO;
import ro.oks.bankend.exceptions.CommandNotFoundException;
import ro.oks.bankend.exceptions.CustomerNotFoundException;
import ro.oks.bankend.web.interfacesWeb.utils.Constants;

import java.util.List;

public interface CommandApiInterface {
    String URL = "/commands";
    String ID = "/{commandId}";

    @GetMapping(Constants.BASE_URL + URL)
    List<CommandDTO> getCommands();

    @GetMapping(Constants.BASE_URL + URL + ID)
    CommandDTO getCommand(@PathVariable String commandId) throws CommandNotFoundException;

    @GetMapping(Constants.BASE_URL + URL + "/addFor/{customerId}")
    CommandDTO addCommand(@PathVariable String customerId)
            throws CustomerNotFoundException;

    @PutMapping(Constants.BASE_URL + URL + ID + "/update")
    CommandDTO updateCommand(@PathVariable String commandId, @RequestBody CommandDTO commandDTO)
            throws CommandNotFoundException;

    @DeleteMapping(Constants.BASE_URL + URL + ID + "/delete")
    void deleteCommand(@PathVariable String commandId) throws CommandNotFoundException;

}
