package be.ucll.restaurant.service;

import be.ucll.restaurant.db.DagMenuRepo;
import be.ucll.restaurant.db.GerechtRepo;
import be.ucll.restaurant.db.WeekMenuRepo;
import be.ucll.restaurant.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@org.springframework.stereotype.Service
public class Service {
    @Autowired
    GerechtRepo gerechtRepo;

    @Autowired
    DagMenuRepo dagMenuRepo;

    @Autowired
    WeekMenuRepo weekMenuRepo;


    public Service() {
    }
                        //////////////////////
                        /////////////////////
                        //GERECHT functies///
                        ////////////////////
                        ////////////////////

    public List<Gerecht> getAllGerechts() {return gerechtRepo.findAll();}
    public Gerecht findGerechtById(int gerechtId) {
        return gerechtRepo.findById(gerechtId).orElseThrow(IllegalArgumentException::new);
    }
    public Gerecht addGerecht(Gerecht gerecht) {
        Gerecht toBeAdded = gerechtRepo.findByBesch(gerecht.getBeschrijving());

        if (toBeAdded != null) {
            gerecht.setId(toBeAdded.getId());
        }
       return gerechtRepo.save(gerecht);
    }
    public void deleteGerecht(Gerecht gerecht) {
        boolean gevonden = false;
        for (int i = 0; i < dagMenuRepo.findAll().size(); i++) {
            DagMenu d = dagMenuRepo.findAll().get(i);
            List<Gerecht> gerechts = new ArrayList <>();
            gerechts.add(d.getSoep());
            gerechts.add( d.getDagschotel());
            gerechts.add(d.getVeggie());
            for (Gerecht g : gerechts) {
                if (g.getId() == gerecht.getId()) gevonden = true;
            }
        }

        if (!gevonden) {
        gerechtRepo.delete(gerecht);
        } else {
            throw new IllegalArgumentException("Gewenste Gerecht Is Nog In Een DagMenu Aanwezig.");
        }

    }
    public Gerecht updateGerecht(Gerecht updatedGerecht, int gId) {
        if (gerechtRepo.findById(gId).isPresent()) {
            updatedGerecht.setId(gerechtRepo.findById(gId).get().getId());
            gerechtRepo.save(updatedGerecht);
        } else {
            throw new IllegalArgumentException("Geen gerecht met gegeven id gevonden.");
        }
        return updatedGerecht;
    }

                        //////////////////////
                        /////////////////////
                        //DAGMENU functies///
                        ////////////////////
                        ////////////////////


    public List<DagMenu> getAllDagmenus() {
        return dagMenuRepo.findAll();
    }

    public WeekMenu addDagMenu(DagMenu dagMenu) {

        checkInDBvoorBestaandeGerechten(dagMenu);


        dagMenu.setSoep(gerechtRepo.findByBesch(dagMenu.getSoep().getBeschrijving()));
        dagMenu.setDagschotel(gerechtRepo.findByBesch(dagMenu.getDagschotel().getBeschrijving()));
        dagMenu.setVeggie(gerechtRepo.findByBesch(dagMenu.getVeggie().getBeschrijving()));

        int weekId = dagMenu.getYearAndWeekNumber();

        WeekMenu weekMenu = weekMenuRepo.findById(weekId).orElse(new WeekMenu());

        if (weekMenu.getId() == 0) {
            weekMenu.setId(weekId);

            List<DagMenu> dagMenus = new ArrayList <>();
            dagMenus.add(dagMenu);
            weekMenu.setDagMenus(dagMenus);
        } else {
            weekMenu.addDagmenu(dagMenu);
        }
        orderWeekMenuDagen(weekMenu);
        return weekMenuRepo.save(weekMenu);
    }
    public Optional <DagMenu> updateDagmenu(String datum, DagMenu teUpdatenDagMenu) {
        teUpdatenDagMenu.setDatum(datum);

        DagMenu vorigeVersie = dagMenuRepo.findById(datum).orElseThrow(IllegalArgumentException::new);

        teUpdatenDagMenu.setDag(vorigeVersie.getDag());
        checkInDBvoorBestaandeGerechten(teUpdatenDagMenu);
        return dagMenuRepo.findById(datum);
    }
                               //////////////////////
                               /////////////////////
                               //WEEKMENU functies///
                               ////////////////////
                               ////////////////////
   public List<WeekMenu> weekMenus() {
       return weekMenuRepo.findAll();
   }
                                //////////////////////
                                /////////////////////
                                //Extra functies///
                                ////////////////////
                                ///////////////////


    private WeekMenu orderWeekMenuDagen(WeekMenu weekMenu) {
        List<DagMenu> dagMenus = weekMenu.getDagMenus();

        Collections.sort(dagMenus, new Comparator<DagMenu>() {
            public int compare(DagMenu o1, DagMenu o2) {
                if (o1.getDatum() == null || o2.getDatum() == null)
                    return 0;
                return o1.getDatum().compareTo(o2.getDatum());
            }
        });

        weekMenu.setDagMenus(dagMenus);
        return weekMenu;
    }
    private void checkInDBvoorBestaandeGerechten(DagMenu dagMenu) {
        addGerecht(dagMenu.getSoep());
        addGerecht(dagMenu.getDagschotel());
        addGerecht(dagMenu.getVeggie());

        dagMenuRepo.save(dagMenu);
    }


}
