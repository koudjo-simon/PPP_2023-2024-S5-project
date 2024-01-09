package ro.oks.bankend.service.interfacesService;

import ro.oks.bankend.dtos.CustomerDTO;
import ro.oks.bankend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerServiceInterface {
    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomer(String customerId) throws CustomerNotFoundException;
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(String customerId, CustomerDTO customerDTO) throws CustomerNotFoundException;
    void deleteCustomer(String customerId) throws CustomerNotFoundException;
}
