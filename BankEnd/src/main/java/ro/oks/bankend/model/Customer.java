package ro.oks.bankend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Customer {
    @Id
    private String customerId;

    private String firstname;

    private String lastname;

    private int age;

    private String email;

    private String country;

    private String region;

    private String address;

    @OneToMany(mappedBy = "customer")
    List<Command> commands;
}
