package ro.oks.bankend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.oks.bankend.model.Command;

public interface CommandRepository extends JpaRepository<Command, String> {
}
