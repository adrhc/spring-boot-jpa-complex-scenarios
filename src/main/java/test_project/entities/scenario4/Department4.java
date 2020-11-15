package test_project.entities.scenario4;

/**
 * Created by adr on 11/24/15.
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department4 implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    /**
     * The HashMap backing the HashSet will use as map-key the @MapKey specified property.
     */
    @OneToMany(mappedBy = "department4")
    @MapKey(name = "id")
    private Set<Person4> person4s = new HashSet<>();

    public Set<Person4> getPerson4s() {
        return person4s;
    }

    public void setPerson4s(Set<Person4> person4s) {
        this.person4s = person4s;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department4 [id=" + id + ", name=" + name + ", person4s=" + person4s
                + "]";
    }
}