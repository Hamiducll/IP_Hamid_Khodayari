package be.ucll.restaurant.db;

import be.ucll.restaurant.model.WeekMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekMenuRepo extends JpaRepository<WeekMenu, Integer> {
}
