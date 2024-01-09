package ro.oks.bankend.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.oks.bankend.dtos.CustomerDTO;
import ro.oks.bankend.exceptions.CustomerNotFoundException;
import ro.oks.bankend.service.interfacesService.CustomerServiceInterface;
import ro.oks.bankend.web.interfacesWeb.CustomerApiInterface;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomerController implements CustomerApiInterface {
    private final CustomerServiceInterface customerServiceInterface;

    public CustomerController(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    @Override
    public List<CustomerDTO> getCustomers(){
        return customerServiceInterface.getCustomers();
    }

    @Override
    public CustomerDTO getCustomer(String customerId) throws CustomerNotFoundException {
        return customerServiceInterface.getCustomer(customerId);
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        return customerServiceInterface.addCustomer(customerDTO);
    }

    @Override
    public CustomerDTO updateCustomer(String customerId, CustomerDTO customerDTO) throws CustomerNotFoundException {
        return customerServiceInterface.updateCustomer(customerId, customerDTO);
    }

    @Override
    public void deleteCustomer(String customerId) throws CustomerNotFoundException {
        customerServiceInterface.deleteCustomer(customerId);
    }

}
