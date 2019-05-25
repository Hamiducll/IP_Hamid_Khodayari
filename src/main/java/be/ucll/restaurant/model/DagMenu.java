package be.ucll.restaurant.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

@Entity
@Table(schema = "r0716543_restaurant", name = "dagmenu")
public class DagMenu {
    @Id
    @NotNull
    @Column(unique = true)
    private String datum;

    @NotNull
    private Dag dag;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH)
    private Gerecht soep;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH)
    private Gerecht veggie;

    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH)
    private Gerecht dagschotel;

    private int yearAndWeekNumber;

    public DagMenu() {
    }

    public DagMenu(Dag dag, String datum) {
        setDag(dag);
        setDatum(datum);
    }
    public DagMenu(Dag dag,String datum,  Gerecht soep,  Gerecht dagschotel,  Gerecht veggie) {
        setDag(dag);
        setDatum(datum);
        setGerechten(soep, dagschotel, veggie);
    }

    public DagMenu(String datum, Gerecht soep, Gerecht dagschotel, Gerecht veggie) {
        setDatum(datum);
        setGerechten(soep, dagschotel, veggie);
    }


    //// SETTERS /////
    public void setDatum(String datum) {
        this.datum = datum;
        // OM zo de Id van weekMenu te kunnen finden.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(datum, formatter);
        TemporalField weekOfWeekbasedYear = WeekFields.ISO.weekOfWeekBasedYear();
        setYearAndWeekNumber(date.getYear(), date.get(weekOfWeekbasedYear));
    }

    public void setDag(Dag dag) {
        this.dag = dag;
    }
    public void setGerechten(Gerecht soep, Gerecht dagschotel, Gerecht veggie) {
        setSoep(soep);
        setDagschotel(dagschotel);
        setVeggie(veggie);
    }

    public void setVeggie(Gerecht veggie) {
        if (veggie == null) throw new IllegalArgumentException("Veggie is null");

        this.veggie = veggie;
    }

    public void setDagschotel(Gerecht dagschotel) {
        if (dagschotel == null) throw new IllegalArgumentException("Dagschotel is null");

        this.dagschotel = dagschotel;
    }
    public void setSoep(Gerecht soep) {
        if (soep == null) throw new IllegalArgumentException("Soep is null");

        this.soep = soep;
    }





    ///GETTERS////

    @JsonProperty("datum")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public String getDatum() {
        return datum;
    }

    @JsonProperty("dag")
    public String getWeekDagAsString() {
        switch (dag){
            case MAANDAG: return "Maandag";
            case DINSDAG: return "Dinsdag";
            case WOENSDAG: return "Woensdag";
            case DONDERDAG: return "Donderdag";
            case VRIJDAG: return "Vrijdag";
            case ZATERDAG: return "Zaterdag";
            case ZONDEAG: return "Zondag";
            default: return "Geen bestaande dag.";
        }
    }

    public Dag getDag() {
        return dag;
    }

    public Gerecht getSoep() {
        return soep;
    }

    public Gerecht getVeggie() {
        return veggie;
    }

    public Gerecht getDagschotel() {
        return dagschotel;
    }
    @JsonIgnore
    public int getYearAndWeekNumber() { return yearAndWeekNumber; }
    public void setYearAndWeekNumber(int year, int weekNumber) {
        String y = String.valueOf(year);
        // make sure we have a zero in front of a 1 digit weekNumber
        String wn = String.format("%02d", weekNumber);
        // this puts the year and the week after each other:
        // eg. 201917 for year 2019 and week 17 or 201907 for year 2019 and week 7
        this.yearAndWeekNumber = Integer.parseInt(y + wn);
    }
}
