package ro.oks.bankend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.oks.bankend.model.Food;

public interface FoodRepository extends JpaRepository<Food, String> {
}
