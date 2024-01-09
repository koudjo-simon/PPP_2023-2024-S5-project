package ro.oks.bankend.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.oks.bankend.exceptions.CommandLineNotFoundException;
import ro.oks.bankend.exceptions.CommandNotFoundException;
import ro.oks.bankend.exceptions.CustomerNotFoundException;
import ro.oks.bankend.exceptions.FoodNotFoodException;
import ro.oks.bankend.model.Command;
import ro.oks.bankend.model.CommandLine;
import ro.oks.bankend.model.Customer;
import ro.oks.bankend.model.Food;
import ro.oks.bankend.repositories.CommandLineRepository;
import ro.oks.bankend.repositories.CommandRepository;
import ro.oks.bankend.repositories.CustomerRepository;
import ro.oks.bankend.repositories.FoodRepository;


@Service
@Slf4j
public class VerifyEntity {

    private FoodRepository foodRepository;
    private CustomerRepository customerRepository;
    private CommandRepository commandRepository;
    private CommandLineRepository commandLineRepository;

    public VerifyEntity(FoodRepository foodRepository,
                        CustomerRepository customerRepository,
                        CommandRepository commandRepository,
                        CommandLineRepository commandLineRepository) {
        this.foodRepository = foodRepository;
        this.customerRepository = customerRepository;
        this.commandRepository = commandRepository;
        this.commandLineRepository = commandLineRepository;
    }

    protected Food verifyFood(String foodId) throws FoodNotFoodException {
        log.info("Checking food with id = " + foodId);
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoodException("Food with id = "+foodId+" not Found"));
        return food;
    }

    protected Customer verifyCustomer(String customerId) throws CustomerNotFoundException {
        log.info("Checking customer with id = "+customerId);
        Customer customer = customerRepository
                .findById(customerId).orElseThrow(
                        () -> new CustomerNotFoundException("Customer with id = "+customerId+" is not found")
                );
        return customer;
    }

    protected Command verifyCommand(String commandId) throws CommandNotFoundException {
        return commandRepository.findById(commandId)
                .orElseThrow(
                        () -> new CommandNotFoundException("Command with id = "+commandId+" not found")
                );
    }

    public CommandLine verifyCommandLine(String commandLineId) throws CommandLineNotFoundException {
        return commandLineRepository.findById(commandLineId)
                .orElseThrow(
                        () -> new CommandLineNotFoundException("Command Line with id = "+commandLineId+" not found")
                );
    }
}
