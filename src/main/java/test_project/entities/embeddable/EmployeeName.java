package test_project.entities.embeddable;

/**
 * Created by adr on 11/20/15.
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeName {
    @Column(name = "F_NAME", insertable = false, updatable = false)
    private String firstName;
    @Column(name = "L_NAME", insertable = false, updatable = false)
    private String lastName;

    public EmployeeName() {
    }

    public EmployeeName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }
}