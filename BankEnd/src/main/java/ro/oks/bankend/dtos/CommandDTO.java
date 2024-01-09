package ro.oks.bankend.dtos;

import lombok.Data;
import ro.oks.bankend.model.model_utils.CommandStatus;

import java.util.Date;

@Data
public class CommandDTO {
    private String commandId;
    private CustomerDTO customerDTO;
    private double totalCommandPrice;
    private Date commandDate;
    private CommandStatus commandStatus;
    private Date lastModifiedDate;
}
