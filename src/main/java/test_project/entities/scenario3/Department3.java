package test_project.entities.scenario3;

/**
 * Created by adr on 11/24/15.
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany
    @JoinTable(name = "PERSON_DEPT", joinColumns = @JoinColumn(name = "EMP_ID"), inverseJoinColumns = @JoinColumn(name = "PHONE_ID"))
    private Set<Person3> person3s = new HashSet<>();

    public Set<Person3> getPerson3s() {
        return person3s;
    }

    public void setPerson3s(Set<Person3> person3s) {
        this.person3s = person3s;
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
        return "Department3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", person3s=" + person3s +
                '}';
    }
}