package ro.oks.bankend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.oks.bankend.dtos.CustomerDTO;
import ro.oks.bankend.exceptions.CustomerNotFoundException;
import ro.oks.bankend.mappers.CustomerMapper;
import ro.oks.bankend.model.Customer;
import ro.oks.bankend.repositories.CustomerRepository;
import ro.oks.bankend.service.interfacesService.CustomerServiceInterface;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;
    private final VerifyEntity verifyEntity;

    public CustomerService(CustomerRepository customerRepository, VerifyEntity verifyEntity) {
        this.customerRepository = customerRepository;
        this.verifyEntity = verifyEntity;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        log.info("Get all customers");
        return customerRepository.findAll().stream()
                .map(CustomerMapper::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(String customerId) throws CustomerNotFoundException {
        log.info("Get customers with id = "+customerId);
        return CustomerMapper.convertToCustomerDTO(verifyEntity.verifyCustomer(customerId));
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        log.info("Adding Customer ...");
        customerDTO.setCustomerId(UUID.randomUUID().toString());
        return CustomerMapper.convertToCustomerDTO(
                customerRepository.save(CustomerMapper.convertToCustomer(customerDTO)));
    }

    @Override
    public CustomerDTO updateCustomer(String customerId, CustomerDTO customerDTO) throws CustomerNotFoundException {
        log.info("Updating Customer with id = "+customerId);
        Customer customer = verifyEntity.verifyCustomer(customerId);
        customerDTO.setCustomerId(customer.getCustomerId());
        return CustomerMapper.convertToCustomerDTO(
                customerRepository.save(CustomerMapper.convertToCustomer(customerDTO)));
    }

    @Override
    public void deleteCustomer(String customerId) throws CustomerNotFoundException {
        log.info("Deleting Customer with id = "+customerId);
            verifyEntity.verifyCustomer(customerId);
            customerRepository.deleteById(customerId);
    }
}
