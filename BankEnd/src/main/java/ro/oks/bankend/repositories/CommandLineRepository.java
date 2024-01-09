package ro.oks.bankend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.oks.bankend.model.CommandLine;

public interface CommandLineRepository extends JpaRepository<CommandLine, String> {
    List<CommandLine> findByCommandCommandId(String commandId);
}
