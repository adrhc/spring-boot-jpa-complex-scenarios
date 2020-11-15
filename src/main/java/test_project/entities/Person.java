package test_project.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian.petre on 09-11-2015.
 */
@Entity
@Table(name = "T_PERSON")
@DiscriminatorColumn(name = "PERSON_TYPE", discriminatorType = DiscriminatorType.INTEGER)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private IdCard idCard;
    private List<Phone> phones = new ArrayList<>();

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CARD_ID")
    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idCard=" + idCard +
                ", phones=" + phones +
                '}';
    }
}