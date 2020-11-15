package test_project.entities.scenario3;

/**
 * Created by adr on 11/24/15.
 */

import javax.persistence.*;

@Entity
public class Person3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department3 department3;

    public Person3() {
    }

    public Person3(String name) {
        this.name = name;
    }

    public Department3 getDepartment3() {
        return department3;
    }

    public void setDepartment3(Department3 department3) {
        this.department3 = department3;
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
        return "Person3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}