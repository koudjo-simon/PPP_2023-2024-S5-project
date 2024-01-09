package ro.oks.bankend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.oks.bankend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
