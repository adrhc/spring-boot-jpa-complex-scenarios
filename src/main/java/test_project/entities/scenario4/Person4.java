package test_project.entities.scenario4;

/**
 * Created by adr on 11/24/15.
 */

import javax.persistence.*;

@Entity
public class Person4 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Department4 department4;

    public Person4() {
    }

    public Person4(String name) {
        this.name = name;
    }


    public Department4 getDepartment4() {
        return department4;
    }

    public void setDepartment4(Department4 department4) {
        this.department4 = department4;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Person4 [id=" + id + ", name=" + name + "]";
    }

}