package ro.oks.bankend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ro.oks.bankend.model.model_utils.CommandStatus;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Command {

    @Id
    private String commandId;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "command")
    private List<CommandLine> commandLines;

    @Column(name = "ttlCmp")
    private double totalCommandPrice;

    private Date commandDate;

    @Enumerated(EnumType.STRING)
    private CommandStatus commandStatus;

    private Date lastModifiedDate;

}
