package test_project.entities;

import test_project.entities.embeddable.EmployeeName;
import test_project.entities.embeddable.EmployeeName1;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adr on 11/19/15.
 */
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    /**
     * create table emp_seniority (
     * department_id integer not null,
     * seniority integer,
     * emp_id integer not null,
     * primary key (department_id, emp_id)
     * )
     */
    @ElementCollection
    @CollectionTable(name = "EMP_SENIORITY")
    @MapKeyJoinColumn(name = "EMP_ID")
    @Column(name = "SENIORITY")
    private Map<Employee, Integer> seniorities;

    @OneToMany(mappedBy = "department")
    private Map<EmployeeName, Employee> employeesByName;

    @OneToMany(mappedBy = "department")
    private Map<EmployeeName1, EmployeeWithEmbeddedName> employeeWithEmbeddedNameMap;

    public Department() {
        seniorities = new HashMap<>();
    }

    public Map<EmployeeName1, EmployeeWithEmbeddedName> getEmployeeWithEmbeddedNameMap() {
        return employeeWithEmbeddedNameMap;
    }

    public void setEmployeeWithEmbeddedNameMap(Map<EmployeeName1, EmployeeWithEmbeddedName> employeeWithEmbeddedNameMap) {
        this.employeeWithEmbeddedNameMap = employeeWithEmbeddedNameMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String deptName) {
        this.name = deptName;
    }

    public Map<Employee, Integer> getEmployees() {
        return seniorities;
    }

    public void setEmployeeSeniority(Employee employee, int seniority) {
        seniorities.put(employee, seniority);
    }

    public void removeEmployee(Employee employee) {
        seniorities.remove(employee);
    }

    public Map<EmployeeName, Employee> getEmployeesByName() {
        return employeesByName;
    }

    public void setEmployeesByName(Map<EmployeeName, Employee> employeesByName) {
        this.employeesByName = employeesByName;
    }

    public String toString() {
        StringBuffer aBuffer = new StringBuffer("Department ");
        aBuffer.append(" id: ");
        aBuffer.append(id);
        aBuffer.append(" name: ");
        aBuffer.append(name);
        aBuffer.append(" employeeCount: ");
        aBuffer.append(seniorities.size());
        return aBuffer.toString();
    }
}