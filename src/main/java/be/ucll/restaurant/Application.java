package be.ucll.restaurant;

import be.ucll.restaurant.db.DagMenuRepo;
import be.ucll.restaurant.db.GerechtRepo;
import be.ucll.restaurant.db.WeekMenuRepo;
import be.ucll.restaurant.model.*;
import be.ucll.restaurant.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner{
    @Autowired
    private DagMenuRepo dagMenuRepo;

    @Autowired
    private WeekMenuRepo weekMenuRepo;

    @Autowired
    private GerechtRepo gerechtRepo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        WeekMenu weekMenu = new WeekMenu();

        DagMenu d1 = new DagMenu(Dag.MAANDAG ,"18-02-2019");
        DagMenu d2 = new DagMenu(Dag.DINSDAG ,"19-02-2019");

        Gerecht bloemkoolsoep = new Gerecht("Bloemkoolsoep", 1, GerechtType.SOEP);
        Gerecht cordonBlueDagSchote = new Gerecht("Cordon Blue", 4.2, GerechtType.DAGSCHOTEL);
        Gerecht vegigiePasta = new Gerecht("Vegigie Pasta", 4, GerechtType.VEGGIE);
        Gerecht tomatensoep = new Gerecht("Tomatensoep", 1, GerechtType.SOEP);
        Gerecht konijnmetpruimen  = new Gerecht("Konijn met pruimen", 4, GerechtType.DAGSCHOTEL);
        Gerecht groentenLasagne= new Gerecht("Groenten Lasagne", 2.2, GerechtType.VEGGIE);


        d1.setGerechten(bloemkoolsoep, cordonBlueDagSchote, vegigiePasta);
        d2.setGerechten(tomatensoep, konijnmetpruimen, groentenLasagne);

        weekMenu.setId(d1.getYearAndWeekNumber());

        weekMenu.addDagmenu(d1);
        weekMenu.addDagmenu(d2);

        gerechtRepo.save(bloemkoolsoep);
        gerechtRepo.save(cordonBlueDagSchote);
        gerechtRepo.save(vegigiePasta);
        gerechtRepo.save(tomatensoep);
        gerechtRepo.save(konijnmetpruimen);
        gerechtRepo.save(groentenLasagne);

        dagMenuRepo.save(d1);
        dagMenuRepo.save(d2);

        weekMenuRepo.save(weekMenu);

    }
}
