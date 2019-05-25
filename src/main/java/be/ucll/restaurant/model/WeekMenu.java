package be.ucll.restaurant.model;
import javax.persistence.*;
import java.util.*;
@Entity
@Table( schema = "r0716543_restaurant",name = "weekmenu")
public class WeekMenu {

    @Id
    @Column(unique = true)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dagMenuWeekId", referencedColumnName = "id")
    private List<DagMenu> dagMenus = new ArrayList<>();

    public WeekMenu() {}
    public WeekMenu(int id) {setId(id);}
    public void setId(int id ) {this.id = id;}
    public List<DagMenu> getDagMenus() {return dagMenus;}
    public void setDagMenus(List <DagMenu> dagMenus) {
        this.dagMenus= dagMenus;
    }
    public void addDagmenu(DagMenu dagMenu) {        this.dagMenus.add(dagMenu);    }
    public void deleteDagMenu(DagMenu dagMenu) {        this.dagMenus.remove(dagMenu);    }
    public int getId() {        return id;    }

}
