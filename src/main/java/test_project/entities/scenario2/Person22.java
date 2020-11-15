package test_project.entities.scenario2;

import javax.persistence.*;

/**
 * Created by adr on 11/23/15.
 */
@Entity
public class Person22 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn
    private DepartmentY departmenty;

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

    public DepartmentY getDepartmenty() {
        return departmenty;
    }

    public void setDepartmenty(DepartmentY departmenty) {
        this.departmenty = departmenty;
    }

    @Override
    public String toString() {
        return "Person22{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmenty=" + departmenty +
                '}';
    }
}
