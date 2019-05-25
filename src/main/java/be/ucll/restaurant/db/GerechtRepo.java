package be.ucll.restaurant.db;


import be.ucll.restaurant.model.Gerecht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GerechtRepo extends JpaRepository<Gerecht, Integer> {
    @Query(value = "select * from r0716543_restaurant.gerecht where beschrijving = ?1", nativeQuery = true )
    Gerecht findByBesch(String beschrijving);
}
