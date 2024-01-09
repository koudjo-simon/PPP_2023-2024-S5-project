package ro.oks.bankend.mappers;

import org.springframework.beans.BeanUtils;
import ro.oks.bankend.dtos.CommandLineDTO;
import ro.oks.bankend.model.CommandLine;

public class CommandLineMapper {

    public static CommandLine convertToCommandLine(CommandLineDTO commandLineDTO){
        CommandLine commandLine = new CommandLine();
        BeanUtils.copyProperties(commandLineDTO, commandLine);
        commandLine.setCommand(CommandMapper.confertToCommand(commandLineDTO.getCommandDTO()));
        commandLine.setFood(FoodMapper.convertToFood(commandLineDTO.getFoodDTO()));
        return commandLine;
    }

    public static CommandLineDTO convertToCommandLineDTO(CommandLine commandLine){
        CommandLineDTO commandLineDTO = new CommandLineDTO();
        BeanUtils.copyProperties(commandLine, commandLineDTO);
        commandLineDTO.setCommandDTO(CommandMapper.convertToCommandDTO(commandLine.getCommand()));
        commandLineDTO.setFoodDTO(FoodMapper.convertToFoodDTO(commandLine.getFood()));
        return commandLineDTO;
    }

}
