package be.ucll.restaurant.controller;

import be.ucll.restaurant.Application;
import be.ucll.restaurant.db.DagMenuRepo;
import be.ucll.restaurant.db.GerechtRepo;
import be.ucll.restaurant.db.WeekMenuRepo;
import be.ucll.restaurant.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class RestControllerIntegrationTest {

    @Autowired
    private MockMvc restController;

    @Autowired
    private WeekMenuRepo weekMenuRepo;

    @Autowired
    private DagMenuRepo dagMenuRepo;

    @Autowired
    private GerechtRepo gerechtRepo;


    @Test
    public void givenWeekMenus_whenGetWeekmenus_thenStatus200AndJSONofWeekmenus() throws Exception {
        Gerecht soep =  new Gerecht("Spinazie soep", 1.2, GerechtType.SOEP);
        Gerecht dagschotel = new Gerecht("KipCurry", 4, GerechtType.DAGSCHOTEL);
        Gerecht veggie= new Gerecht("AshSoep", 2, GerechtType.VEGGIE);
        DagMenu dagMenu = new DagMenu(Dag.DONDERDAG, "24-12-2019", soep, dagschotel, veggie);
        gerechtRepo.save(soep);
        gerechtRepo.save(dagschotel);
        gerechtRepo.save(veggie);
        createTestWeekMenu(dagMenu);

        restController.perform(get("/weekmenu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "\t{\n" +
                        "\t\t\"id\":201908,\n" +
                        "\t\t\"dagMenus\":[\n" +
                        "\t\t\t{\"dag\":\"Maandag\",\n" +
                        "\t\t\t\t\"soep\":{\"beschrijving\":\"Bloemkoolsoep\",\"prijs\":1.0,\"type\":\"SOEP\"},\n" +
                        "\t\t\t\t\"veggie\":{\"beschrijving\":\"Vegigie Pasta\",\"prijs\":4.0,\"type\":\"VEGGIE\"},\n" +
                        "\t\t\t\t\"dagschotel\":{\"beschrijving\":\"Cordon Blue\",\"prijs\":4.2,\"type\":\"DAGSCHOTEL\"},\n" +
                        "\t\t\t\"datum\":\"18-02-2019\"},\n" +
                        "\t\t\t{\"dag\":\"Dinsdag\",\n" +
                        "\t\t\t\t\"soep\": {\"beschrijving\":\"Tomatensoep\",\"prijs\":1.0,\"type\":\"SOEP\"},\n" +
                        "\t\t\t\t\"veggie\":{\"beschrijving\":\"Groenten Lasagne\",\"prijs\":2.2,\"type\":\"VEGGIE\"},\n" +
                        "\t\t\t\t\"dagschotel\":{\"beschrijving\":\"Konijn met pruimen\",\"prijs\":4.0,\"type\":\"DAGSCHOTEL\"},\n" +
                        "\t\t\t\"datum\":\"19-02-2019\"}]\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\":201952,\n" +
                        "\t\t\"dagMenus\":[\n" +
                        "\t\t\t{\"dag\":\"Donderdag\",\n" +
                        "\t\t\t\"soep\":{\"beschrijving\":\"Spinazie soep\",\"prijs\":1.2,\"type\":\"SOEP\"},\n" +
                        "\t\t\t\"veggie\":{\"beschrijving\":\"AshSoep\",\"prijs\":2.0,\"type\":\"VEGGIE\"},\n" +
                        "\t\t\t\"dagschotel\":{\"beschrijving\":\"KipCurry\",\"prijs\":4.0,\"type\":\"DAGSCHOTEL\"},\n" +
                        "\t\t\t\"datum\":\"24-12-2019\"}]\n" +
                        "\t}\n" +
                        "]"));
    }

    private void createTestWeekMenu(DagMenu dagMenu) {
        WeekMenu weekMenu = new WeekMenu();
        weekMenu.getDagMenus().add(dagMenu);
        weekMenu.setId(dagMenu.getYearAndWeekNumber());
        dagMenuRepo.save(dagMenu);
        weekMenuRepo.saveAndFlush(weekMenu);
    }

}
