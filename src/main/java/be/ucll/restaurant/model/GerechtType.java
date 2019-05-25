package be.ucll.restaurant.model;

public enum GerechtType {
    SOEP("soep"),
    DAGSCHOTEL("dagschotel"),
    VEGGIE("veggie");

    private String type;

    GerechtType(String type) {
        this.type = type;
    }
    public String getType() {return  this .type;}
}
