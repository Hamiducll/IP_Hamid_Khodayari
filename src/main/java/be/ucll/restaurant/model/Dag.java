package be.ucll.restaurant.model;

public enum Dag {
    MAANDAG("Maandag"),
    DINSDAG("Dinsdag"),
    WOENSDAG("Woensdag"),
    DONDERDAG("Donderdag"),
    VRIJDAG("Vrijdag"),
    ZATERDAG("Zaterdag"),
    ZONDEAG("Zondag");

    private String dag;

    Dag(String dag) {
        this.dag = dag;
    }
    public String getDag() {return  this .dag;}
}
