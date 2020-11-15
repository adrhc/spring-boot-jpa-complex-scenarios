package test_project.entities.scenario2;

import javax.persistence.*;

/**
 * Created by adr on 11/22/15.
 */
@Entity
public class Person21 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "PERS_DEPT_ID_2")
    private Department2 department2;

    @OneToOne
    @JoinColumn(name = "PERS_DEPT_ID_Z")
    private DepartmentZ departmentz;

    public Person21() {
    }

    public Person21(String name) {
        this.name = name;
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

    public Department2 getDepartment2() {
        return department2;
    }

    public void setDepartment2(Department2 department2) {
        this.department2 = department2;
    }

    public DepartmentZ getDepartmentz() {
        return departmentz;
    }

    public void setDepartmentz(DepartmentZ departmentz) {
        this.departmentz = departmentz;
    }

    @Override
    public String toString() {
        return "Person21{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department2 +
                ", departmentz=" + departmentz +
                '}';
    }

}