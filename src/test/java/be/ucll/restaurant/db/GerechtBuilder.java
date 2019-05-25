package be.ucll.restaurant.db;

import be.ucll.restaurant.model.Gerecht;
import be.ucll.restaurant.model.GerechtType;

public class GerechtBuilder {
    private String beschrijving;
    private double prijs;
    private GerechtType type;

    private GerechtBuilder() {}

    public static GerechtBuilder aGerecht() {
        return new GerechtBuilder();
    }

    public static GerechtBuilder anOkGerecht() {
        return aGerecht().withBesch("Flafel").withPrijs(4.5).withGerchtType(GerechtType.VEGGIE);
    }

    public  GerechtBuilder withBesch(String flafel) {
        this.beschrijving = flafel;
        return this;
    }
    public  GerechtBuilder withPrijs(double prijs) {
        this.prijs = prijs;
        return this;
    }
    public  GerechtBuilder withGerchtType(GerechtType type) {
        this.type = type;
        return this;
    }

    public Gerecht build() {
        Gerecht gerecht = new Gerecht();
        gerecht.setBeschrijving(beschrijving);
        gerecht.setPrijs(prijs);
        gerecht.setType(type);
        return gerecht;
    }
}

