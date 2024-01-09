package ro.oks.bankend.dtos;

import lombok.Data;

@Data
public class CustomerDTO {

    private String customerId;

    private String firstname;

    private String lastname;

    private int age;

    private String email;

    private String country;

    private String region;

    private String address;
}
