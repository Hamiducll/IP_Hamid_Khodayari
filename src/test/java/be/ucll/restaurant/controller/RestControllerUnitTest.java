package be.ucll.restaurant.controller;

import be.ucll.restaurant.db.DagMenuRepo;
import be.ucll.restaurant.db.GerechtRepo;
import be.ucll.restaurant.db.WeekMenuRepo;
import be.ucll.restaurant.model.*;
import be.ucll.restaurant.service.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestController.class)
public class RestControllerUnitTest {
    @Autowired
    private MockMvc restController;

    @MockBean
    private Service service;


    @MockBean
    private DagMenuRepo dagMenuRepo;

    @MockBean
    private GerechtRepo gerechtRepo;

    @MockBean
    private WeekMenuRepo weekMenuRepo;

    @Test
    public void givenWeekmenus_WhenGetWeekMenus_thenReturnJsonArray() throws Exception {
        WeekMenu ok = WeekMenuBuilder.anOKWeekMenu().build();
        WeekMenu okk = new WeekMenu();
        Gerecht soep =  new Gerecht("Groentensoep", 1.0, GerechtType.SOEP);
        Gerecht dagschotel = new Gerecht("KipCurry", 4, GerechtType.DAGSCHOTEL);
        Gerecht veggie= new Gerecht("AshSoep", 2, GerechtType.VEGGIE);
        DagMenu dagMenu = new DagMenu(Dag.ZATERDAG, "08-05-2019", soep, dagschotel, veggie);
        okk.setId(dagMenu.getYearAndWeekNumber());
        okk.getDagMenus().add(dagMenu);

        when(service.weekMenus()).thenReturn(Arrays.asList(ok, okk));

        restController.perform(get("/weekmenu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\n" +
                        "\"id\":201908,\n" +
                        "\"dagMenus\":[\n" +
                        "\t{\"dag\":\"Dinsdag\",\n" +
                        "\t\t\"soep\":{\"beschrijving\":\"Bloemkoolsoep\",\"prijs\":2.0,\"type\":\"SOEP\"},\n" +
                        "\t\t\"veggie\":{\"beschrijving\":\"Veggie pasta\",\"prijs\":3.4,\"type\":\"VEGGIE\"},\n" +
                        "\t\t\"dagschotel\":{\"beschrijving\":\"Dagschotel 1\",\"prijs\":4.0,\"type\":\"DAGSCHOTEL\"},\n" +
                        "\t\"datum\":\"19-02-2019\"}\n" +
                        "\t]\n" +
                        "},\n" +
                        "{\n" +
                        "\"id\":201919,\n" +
                        "\"dagMenus\":[\n" +
                        "\t{\"dag\":\"Zaterdag\",\n" +
                        "\t\t\"soep\":{\"beschrijving\":\"Groentensoep\",\"prijs\":1.0,\"type\":\"SOEP\"},\n" +
                        "\t\t\"veggie\":{\"beschrijving\":\"AshSoep\",\"prijs\":2.0,\"type\":\"VEGGIE\"},\n" +
                        "\t\t\"dagschotel\":{\"beschrijving\":\"KipCurry\",\"prijs\":4.0,\"type\":\"DAGSCHOTEL\"},\n" +
                        "\t\"datum\":\"08-05-2019\"}\n" +
                        "\t]\n" +
                        "}]"));
    }





}
