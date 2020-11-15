package test_project.entities.scenario7;

import javax.persistence.*;
import java.util.List;

/**
 * Created by adriana on 29-Nov-15.
 */
@Entity
public class Country7 {
    private int id;
    private String name;
    private List<Person7> persons;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country")
    public List<Person7> getPersons() {
        return persons;
    }

    public void setPersons(List<Person7> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Country7{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
