package ro.oks.bankend.dtos;

import lombok.Data;

@Data
public class CommandLineDTO {
    private String commandLineId;
    private FoodDTO foodDTO;
    private CommandDTO commandDTO;
    private double unitPrice;
    private double quantity;
    private double totalCommandLinePrice;

}
