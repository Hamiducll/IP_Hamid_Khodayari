package be.ucll.restaurant.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Currency;

import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(schema = "r0716543_restaurant", name = "gerecht")
public class Gerecht {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //  gerechtId automatisch genereren, geen AtomicInteger
    @Column(unique = true)
    private int id;

    @NotEmpty
    @Size(min=2, max = 20)
    @Column(unique = true)
    private String beschrijving;

    @NotNull
    @DecimalMax("10.0")
    @DecimalMin("0.1")
    private double prijs;

    @NotNull
    private GerechtType type;


    public Gerecht() { }
    public Gerecht(String beschrijving, double prijs, GerechtType type) {
        setType(type);
        setBeschrijving(beschrijving);
        setPrijs(prijs);
    }

    public GerechtType getType() {
        return this.type;
    }
    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        if(prijs < 0.1 || prijs >10) throw new IllegalArgumentException("Price should be between 0.1 and 10.");
        this.prijs = prijs;
    }
     public void setType( GerechtType type) {
        this.type = type;
     }

    public void setId(int id) {
        this.id = id;
    }
    @JsonIgnore
    public int getId() {
        return id;
    }
}
