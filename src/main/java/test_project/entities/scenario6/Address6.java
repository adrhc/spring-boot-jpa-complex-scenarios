package test_project.entities.scenario6;

import javax.persistence.*;

/**
 * Created by adr on 11/28/15.
 */
@Entity
public class Address6 {
    private Integer id;
    private String address;
    private Person6 person;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne
    public Person6 getPerson() {
        return person;
    }

    public void setPerson(Person6 person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Address6{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
