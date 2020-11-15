package test_project.entities.scenario2;

import javax.persistence.*;

/**
 * Created by adr on 11/22/15.
 */
@Entity
public class DepartmentY {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(mappedBy = "departmenty", fetch = FetchType.LAZY)
    private Person22 person;

    public Person22 getPerson() {
        return person;
    }

    public void setPerson(Person22 person) {
        this.person = person;
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
        return "DepartmentY{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
