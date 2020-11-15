package test_project.entities.scenario2;

import javax.persistence.*;

/**
 * Created by adr on 11/22/15.
 */
@Entity
public class DepartmentZ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "departmentz", fetch = FetchType.LAZY)
    private PersonZ personz;

    public PersonZ getPersonz() {
        return personz;
    }

    public void setPersonz(PersonZ personz) {
        this.personz = personz;
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
        return "DepartmentZ{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
