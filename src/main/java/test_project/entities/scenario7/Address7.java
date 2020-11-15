package test_project.entities.scenario7;

import javax.persistence.*;

/**
 * Created by adr on 11/28/15.
 */
@Entity
public class Address7 {
    private Integer id;
    private String address;
    private Person7 person;

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
    public Person7 getPerson() {
        return person;
    }

    public void setPerson(Person7 person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Address7{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
