package test_project.entities.scenario8;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriana on 30-Nov-15.
 */
@Entity
public class Owner {
    private int id;
    private String name;
    private long version;
    private List<Car> cars = new ArrayList<>();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @OneToMany(mappedBy = "owner")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", cars=" + cars +
                '}';
    }
}
