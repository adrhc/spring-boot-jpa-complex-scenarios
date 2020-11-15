package test_project.entities.scenario2;

import javax.persistence.*;

/**
 * Created by adr on 11/22/15.
 */
@Entity(name = "Department2")
@Table(name = "Department2")
public class Department2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(mappedBy = "department2", fetch = FetchType.LAZY)
    private Person21 person21;

    public Person21 getPerson21() {
        return person21;
    }

    public void setPerson21(Person21 person21) {
        this.person21 = person21;
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
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
