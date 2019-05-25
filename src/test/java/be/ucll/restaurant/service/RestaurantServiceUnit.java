package be.ucll.restaurant.service;

import be.ucll.restaurant.db.DagMenuRepo;
import be.ucll.restaurant.db.GerechtRepo;
import be.ucll.restaurant.db.WeekMenuRepo;
import be.ucll.restaurant.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class RestaurantServiceUnit {

    @TestConfiguration
    static class RestaurantServiceTestContextConfiguration {

        // Creates an instance of service in order to be able to autowire it
        @Bean
        public Service Service() {
            return new Service();
        }
    }

    @Autowired
    private Service service;

    @MockBean
    private GerechtRepo gerechtRepo;

    @MockBean
    private DagMenuRepo dagMenuRepo;

    @MockBean
    private WeekMenuRepo weekMenuRepo;



    private Gerecht gok, gok1, addGok, updatedGerecht, soep, dagschotel, veggie, deleteGerecht;
    private List<Gerecht> gerechts, d1Dagmenus;
    private DagMenu d1, d2, updateDag;
    private List<DagMenu> dagMenus, weekDagmenus;
    private WeekMenu weekMenu, weekMenuu;
    private List<WeekMenu> weekMenus;

    @Before
    public void setUp() {
        gok = new Gerecht("Spinazie soep", 1.2, GerechtType.SOEP);
        gok1 = new Gerecht("KipCurry", 4, GerechtType.DAGSCHOTEL);
        addGok = new Gerecht("AshSoep", 2, GerechtType.VEGGIE);
        updatedGerecht = new Gerecht("AshSoep1", 4, GerechtType.VEGGIE);
        soep = new Gerecht("Bloemkoolsoep", 1, GerechtType.SOEP);
        dagschotel = new Gerecht("Cordon Blue", 4.2, GerechtType.DAGSCHOTEL);
        veggie =  new Gerecht("Vegigie Pasta", 4, GerechtType.VEGGIE);
        deleteGerecht = new Gerecht("delete", 4, GerechtType.SOEP);
        gerechts = new ArrayList <>();
        gerechts.add(gok);
        gerechts.add(gok1);
        gerechts.add(soep);
        gerechts.add(dagschotel);
        gerechts.add(veggie);

        d1Dagmenus = new ArrayList <>();
        d1Dagmenus.add(gok);
        d1Dagmenus.add(gok1);
        d1Dagmenus.add(addGok);

        d1 = new DagMenu(Dag.MAANDAG, "20-05-2019", gok, gok1, addGok);
        d2 = new DagMenu(Dag.WOENSDAG, "23-05-2019", gok, gok1, addGok);
        updateDag = new DagMenu( "20-05-2019" ,soep, dagschotel, veggie);
        dagMenus = new ArrayList <>();
        dagMenus.add(d1);
        dagMenus.add(d2);


        weekMenu = new WeekMenu();
        weekMenu.setId(d1.getYearAndWeekNumber());
        weekMenu.getDagMenus().add(d1);
        weekMenu.getDagMenus().add(d2);
        weekMenus  = new ArrayList <>();
        weekMenus.add(weekMenu);


    }

    @Test
    public void should_get_all_gerechts () {
        // Mock
        Mockito.when(gerechtRepo.findAll()).thenReturn(gerechts);

        //when
        List<Gerecht> foundGerechts = service.getAllGerechts();

        //then
        assertThat(foundGerechts.size()).isEqualTo(5);
        assertThat(foundGerechts).contains(gok);
        assertThat(foundGerechts).contains(gok1);
    }

    @Test
    public void should_add_gerecht () {
        //Mock
        Mockito.when(gerechtRepo.save(addGok)).thenReturn(addGok);

        //when
        Gerecht addedGerecht = service.addGerecht(addGok);

        //then
        assertThat(addedGerecht.getBeschrijving()).isEqualTo("AshSoep");
        assertThat(addedGerecht.getPrijs() == 2);
        assertThat(addedGerecht.getType().equals(GerechtType.VEGGIE));
    }
    @Test
    public void should_deleteGerecht(){
        service.deleteGerecht(deleteGerecht);
        Mockito.verify(gerechtRepo, Mockito.times(1)).delete(deleteGerecht);
    }

    @Test
    public void should_updateGerecht(){
        //Mock
        Mockito.when(gerechtRepo.findById(addGok.getId())).thenReturn(Optional.of(addGok));

        //when
        Gerecht upGere = service.updateGerecht(updatedGerecht, addGok.getId());

        //then
        assertThat(upGere.getBeschrijving()).isEqualTo("AshSoep1");
        assertThat(upGere.getPrijs() == 4);
        assertThat(upGere.getType().equals(GerechtType.VEGGIE));

    }

    @Test
    public void should_get_all_Dagmenus(){
        // Mock
        Mockito.when(dagMenuRepo.findAll()).thenReturn(dagMenus);

        //when
        List<DagMenu> founddag = service.getAllDagmenus();

        //then
        assertThat(founddag.size()).isEqualTo(2);
        assertThat(founddag).contains(d1);
        assertThat(founddag).contains(d2);
    }

    @Test
    public void should_add_Dagmenu(){
        //Mock
        Mockito.when(dagMenuRepo.save(updateDag)).thenReturn(updateDag);
        //when
        WeekMenu weekMenu = service.addDagMenu(updateDag);
        DagMenu addedDagmenu = weekMenu.getDagMenus().get(weekMenu.getDagMenus().size()-1);

        //then
        assertThat(addedDagmenu.getDatum()).isEqualTo(updateDag.getDatum());
        assertThat(addedDagmenu.getDag()).isEqualTo(updateDag.getDag());
        assertThat(addedDagmenu.getSoep()).isEqualTo(updateDag.getSoep());
    }

//    @Test
  //  public void should_update_Dagmenu(){}


    @Test
    public void should_get_all_Weekmenus(){
        // Mock
        Mockito.when(weekMenuRepo.findAll()).thenReturn(weekMenus);

        //when
        List<WeekMenu> foundWeeks = service.weekMenus();

        //then
        assertThat(foundWeeks.size()).isEqualTo(1);
        assertThat(foundWeeks).contains(weekMenu);
    }


}
