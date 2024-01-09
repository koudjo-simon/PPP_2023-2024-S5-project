package ro.oks.bankend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class CommandLine {
    @Id
    private String commandLineId;
    @ManyToOne
    private Food food;
    @ManyToOne
    private Command command;
    private double unitPrice;
    private double quantity;
    private double totalCommandLinePrice;
}
