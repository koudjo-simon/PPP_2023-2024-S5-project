package ro.oks.bankend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.oks.bankend.dtos.CommandLineDTO;
import ro.oks.bankend.exceptions.CommandLineNotFoundException;
import ro.oks.bankend.exceptions.CommandNotFoundException;
import ro.oks.bankend.exceptions.FoodNotFoodException;
import ro.oks.bankend.mappers.CommandLineMapper;
import ro.oks.bankend.model.Command;
import ro.oks.bankend.model.CommandLine;
import ro.oks.bankend.model.Food;
import ro.oks.bankend.repositories.CommandLineRepository;
import ro.oks.bankend.service.interfacesService.CommandLineServiceInterface;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CommandLineService implements CommandLineServiceInterface {

    private final CommandLineRepository commandLineRepository;
    private final VerifyEntity verifyEntity;

    public CommandLineService(CommandLineRepository commandLineRepository, VerifyEntity verifyEntity) {
        this.commandLineRepository = commandLineRepository;
        this.verifyEntity = verifyEntity;
    }

    @Override
    public CommandLineDTO addCommandLine(String foodId, String commandId, double quantity)
            throws FoodNotFoodException, CommandNotFoundException {
        log.info("Adding command Line for command "+commandId);
        Food food = verifyEntity.verifyFood(foodId);
        Command command = verifyEntity.verifyCommand(commandId);
        double unitPrice = food.getPrice();
        double totalCommandLinePrice = unitPrice * quantity;
        CommandLine commandLine = new CommandLine();
        commandLine.setCommandLineId(UUID.randomUUID().toString());
        commandLine.setCommand(command);
        commandLine.setFood(food);
        commandLine.setUnitPrice(unitPrice);
        commandLine.setQuantity(quantity);
        commandLine.setTotalCommandLinePrice(totalCommandLinePrice);
        return CommandLineMapper.convertToCommandLineDTO(commandLineRepository.save(commandLine));
    }
    @Override
    public CommandLineDTO updateCommandLine(String commandLineId, CommandLineDTO commandLineDTO)
            throws CommandLineNotFoundException {
        log.info("Updating command Line with id = "+commandLineId);
        CommandLine commandLine = verifyEntity.verifyCommandLine(commandLineId);
        return CommandLineMapper.convertToCommandLineDTO(commandLineRepository.save(commandLine));
    }
    @Override
    public void deleteCommandLine(String commandLineId) throws CommandLineNotFoundException {
        log.info("Deleting command Line with id = "+commandLineId);
        verifyEntity.verifyCommandLine(commandLineId);
        commandLineRepository.deleteById(commandLineId);
    }

    @Override
    public List<CommandLineDTO> getCommandLines(String commandId) throws CommandNotFoundException {
        log.info("Get command Line with id = "+commandId+" lines");
        verifyEntity.verifyCommand(commandId);
        return commandLineRepository.findByCommandCommandId(commandId).stream()
                .map(CommandLineMapper::convertToCommandLineDTO)
                .collect(Collectors.toList());
    }
}
