package be.ucll.restaurant.db;

import be.ucll.restaurant.model.DagMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DagMenuRepo extends JpaRepository<DagMenu, String> {
    @Query(value = "select * from r0716543_restaurant.dagmenu where datum = ?1", nativeQuery = true )
    DagMenu findByDatum(String datum);
}
