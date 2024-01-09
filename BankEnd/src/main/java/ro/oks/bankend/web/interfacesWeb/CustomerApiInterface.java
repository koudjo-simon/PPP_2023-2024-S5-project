package ro.oks.bankend.web.interfacesWeb;

import org.springframework.web.bind.annotation.*;
import ro.oks.bankend.dtos.CustomerDTO;
import ro.oks.bankend.exceptions.CustomerNotFoundException;
import ro.oks.bankend.web.interfacesWeb.utils.Constants;

import java.util.List;

public interface CustomerApiInterface {

    String URL = "/customers";
    String ID = "/{customerId}";

    @GetMapping(Constants.BASE_URL + URL)
    List<CustomerDTO> getCustomers();

    @GetMapping(Constants.BASE_URL + URL + ID)
    CustomerDTO getCustomer(@PathVariable String customerId) throws CustomerNotFoundException;

    @PostMapping(Constants.BASE_URL + URL + "/add")
    CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO);

    @PutMapping(Constants.BASE_URL + URL + ID + "/update")
    CustomerDTO updateCustomer(@PathVariable String customerId,
                               @RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException;

    @DeleteMapping(Constants.BASE_URL + URL + ID + "/delete")
    void deleteCustomer(@PathVariable String customerId) throws CustomerNotFoundException;
}
