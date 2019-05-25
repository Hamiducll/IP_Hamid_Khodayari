package be.ucll.restaurant.db;


import be.ucll.restaurant.model.Gerecht;
import be.ucll.restaurant.model.GerechtType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class GerechtRepoIntegrationTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GerechtRepo gerechtRepo;

    @Test
    public void should_get_all_gerechts () {
        // given
        Gerecht ok = GerechtBuilder.anOkGerecht().build();
        entityManager.persist(ok);
        entityManager.flush();

        Gerecht nok = new Gerecht("KachalooSoep", 2, GerechtType.SOEP);
        entityManager.persist(nok);
        entityManager.flush();

        // when
        List <Gerecht> gerechtsFound = gerechtRepo.findAll();

        // then
        assertThat(gerechtsFound.size() == 2);
        assertThat(gerechtsFound.contains(ok));
        assertThat(gerechtsFound.contains(nok));
    }
    @Test
    public void should_find_gerecht_by_given_beschrijving () {
        // given
        Gerecht ok = new Gerecht("Aardappelen curry", 4, GerechtType.DAGSCHOTEL);
        entityManager.persist(ok);
        entityManager.flush();

        // when
        Gerecht found = gerechtRepo.findByBesch(ok.getBeschrijving());

        // then
        assertThat(found.getBeschrijving()).isEqualTo(ok.getBeschrijving());
        assertThat(found.getPrijs()).isEqualTo(ok.getPrijs());
        assertThat(found.getType()).isEqualTo(ok.getType());

    }

    @Test
    public void should_add_gerecht () {
        // given
        Gerecht ok = new Gerecht("Aardappelen curry", 4, GerechtType.DAGSCHOTEL);

        // when
        Gerecht addedGerecht = gerechtRepo.save(ok);

        // then
        assertThat(addedGerecht.getBeschrijving()).isEqualTo("Aardappelen curry");
        assertThat(addedGerecht.getPrijs()).isEqualTo(4);
        assertThat(addedGerecht.getType()).isEqualTo(GerechtType.DAGSCHOTEL);
    }



}
