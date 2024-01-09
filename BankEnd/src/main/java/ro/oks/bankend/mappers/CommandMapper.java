package ro.oks.bankend.mappers;

import org.springframework.beans.BeanUtils;
import ro.oks.bankend.dtos.CommandDTO;
import ro.oks.bankend.model.Command;

public class CommandMapper {

    public static Command confertToCommand(CommandDTO commandDTO){
        Command command = new Command();
        BeanUtils.copyProperties(commandDTO, command);
        command.setCustomer(CustomerMapper.convertToCustomer(commandDTO.getCustomerDTO()));
        return command;
    }

    public static CommandDTO convertToCommandDTO(Command command){
        CommandDTO commandDTO = new CommandDTO();
        BeanUtils.copyProperties(command, commandDTO);
        commandDTO.setCustomerDTO(CustomerMapper.convertToCustomerDTO(command.getCustomer()));
        return commandDTO;
    }

}
