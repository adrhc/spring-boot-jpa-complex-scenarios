package test_project.entities.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by adr on 11/23/15.
 */
@Embeddable
public class EmployeeName1 implements Serializable {
    @Column(name = "F_NAME")
    private String firstName;
    @Column(name = "L_NAME")
    private String lastName;

    public EmployeeName1() {
    }

    public EmployeeName1(String firstName, String lastName) {
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