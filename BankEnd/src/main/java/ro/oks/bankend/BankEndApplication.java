package ro.oks.bankend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ro.oks.bankend.config.RsaKeysConfig;
import ro.oks.bankend.dtos.CommandDTO;
import ro.oks.bankend.dtos.CommandLineDTO;
import ro.oks.bankend.dtos.CustomerDTO;
import ro.oks.bankend.dtos.FoodDTO;
import ro.oks.bankend.mappers.CustomerMapper;
import ro.oks.bankend.mappers.FoodMapper;
import ro.oks.bankend.model.Command;
import ro.oks.bankend.model.CommandLine;
import ro.oks.bankend.model.Customer;
import ro.oks.bankend.model.Food;
import ro.oks.bankend.model.model_utils.CommandStatus;
import ro.oks.bankend.model.model_utils.FoodStatus;
import ro.oks.bankend.repositories.CommandLineRepository;
import ro.oks.bankend.repositories.CommandRepository;
import ro.oks.bankend.repositories.CustomerRepository;
import ro.oks.bankend.repositories.FoodRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class BankEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankEndApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                        CommandRepository commandRepository,
                                        FoodRepository foodRepository,
                                        CommandLineRepository commandLineRepository) {
        return args -> {
            Stream.of("Simon", "Robinson", "Josoue", "Romaine").forEach(name -> {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setCustomerId(UUID.randomUUID().toString());
                customerDTO.setAge(5);
                customerDTO.setFirstname(name);
                customerDTO.setLastname(name.toUpperCase());
                customerDTO.setEmail(name + "@email.com");
                customerDTO.setAddress("Address of " + name);
                customerDTO.setCountry("Country of " + name);
                customerDTO.setRegion("Region of " + name);
                customerRepository.save(CustomerMapper.convertToCustomer(customerDTO));
            });
            for (int i = 0; i < 10; i++) {
                FoodDTO foodDTO = new FoodDTO();
                foodDTO.setFoodId(UUID.randomUUID().toString());
                foodDTO.setFavorite(false);
                foodDTO.setFoodStatus(FoodStatus.CREATED);
                foodDTO.setName("Food " + i);
                foodDTO.setStar(Math.random() * 5);
                foodDTO.setOrigins("Food " + i + " origins");
                foodDTO.setPrice(Math.random() * 1000);
                foodDTO.setTags("Food " + i + " tags");
                foodDTO.setImageUrl("Food " + i + " image url");
                foodDTO.setAddDate(new Date());
                foodDTO.setAddDate(new Date());
                foodRepository.save(FoodMapper.convertToFood(foodDTO));
            }
            customerRepository.findAll().stream()
                    .map(customer -> {
                        Command command = new Command();
                        command.setCommandId(UUID.randomUUID().toString());
                        command.setCommandDate(new Date());
                        command.setCommandStatus(CommandStatus.CREATED);
                        command.setLastModifiedDate(new Date());
                        command.setTotalCommandPrice(0);
                        command.setCustomer(customer);
                        commandRepository.save(command);
                        return null;
                    });
            commandRepository.findAll().stream()
                    .map(command -> {
                        for (int i = 0; i < 2; i++) {
                            CommandLine commandLine = new CommandLine();
                            List<Food> foods = foodRepository.findAll();
                            Food food = foods.get((int) (Math.random() * (foods.size() - 1)));
                            double quantity = Math.random() * 5;
                            commandLine.setCommandLineId(UUID.randomUUID().toString());
                            commandLine.setFood(food);
                            commandLine.setCommand(command);
                            commandLine.setQuantity(quantity);
                            commandLine.setUnitPrice(food.getPrice());
                            commandLine.setTotalCommandLinePrice(quantity * food.getPrice());
                        }
                        return null;
                    });
        };
    }

}
