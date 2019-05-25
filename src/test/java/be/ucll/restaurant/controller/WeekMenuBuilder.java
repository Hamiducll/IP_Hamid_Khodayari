package be.ucll.restaurant.controller;

import be.ucll.restaurant.model.*;

import java.util.ArrayList;
import java.util.List;

public class WeekMenuBuilder {

    private int id;
    private List<DagMenu> dagMenus;

    private WeekMenuBuilder(){}

    public static WeekMenuBuilder aWeekMenu() {
        return new WeekMenuBuilder();
    }

    public static WeekMenuBuilder anOKWeekMenu() {
        DagMenu dagMenu = new DagMenu(Dag.DINSDAG, "19-02-2019",
                new Gerecht("Bloemkoolsoep", 2, GerechtType.SOEP),
                new Gerecht("Dagschotel 1", 4, GerechtType.DAGSCHOTEL),
                new Gerecht("Veggie pasta", 3.4, GerechtType.VEGGIE));
        return aWeekMenu().withId(dagMenu.getYearAndWeekNumber()).withDagMenu(dagMenu);
    }

    private WeekMenuBuilder withDagMenu(DagMenu dagMenu) {
        this.dagMenus = new ArrayList <>();
        this.dagMenus.add(dagMenu);
        return this;
    }

    private WeekMenuBuilder withId(int yearAndWeekNumber) {
        this.id = yearAndWeekNumber;
        return this;
    }

    public WeekMenu build() {
        WeekMenu weekMenu = new WeekMenu();
        weekMenu.setId(this.id);
        weekMenu.setDagMenus(this.dagMenus);
        return weekMenu;
    }
}
