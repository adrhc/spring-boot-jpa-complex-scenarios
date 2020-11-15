package test_project.entities.scenario2;

import javax.persistence.*;

/**
 * Created by adr on 11/22/15.
 */
@Entity
public class PersonZ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "PERSZ_DEPT_ID_Z")
    private DepartmentZ departmentz;

    public PersonZ() {
    }

    public PersonZ(String name) {
        this.name = name;
    }

    public DepartmentZ getDepartmentz() {
        return departmentz;
    }

    public void setDepartmentz(DepartmentZ departmentz) {
        this.departmentz = departmentz;
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
}
