package test_project.entities;

import test_project.entities.embeddable.EmployeeName1;

import javax.persistence.*;

/**
 * Created by adr on 11/20/15.
 */
@Entity
public class EmployeeWithEmbeddedName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long salary;

    @Embedded
    private EmployeeName1 employeeName1;

    @ManyToOne
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EmployeeName1 getEmployeeName1() {
        return employeeName1;
    }

    public void setEmployeeName1(EmployeeName1 employeeName1) {
        this.employeeName1 = employeeName1;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
