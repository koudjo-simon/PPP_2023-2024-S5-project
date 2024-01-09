package ro.oks.bankend.mappers;

import org.springframework.beans.BeanUtils;
import ro.oks.bankend.dtos.CustomerDTO;
import ro.oks.bankend.model.Customer;

public class CustomerMapper {

    public static Customer convertToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public static CustomerDTO convertToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

}
