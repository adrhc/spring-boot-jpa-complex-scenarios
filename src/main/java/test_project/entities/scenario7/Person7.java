package test_project.entities.scenario7;

import test_project.enums.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.CascadeType.*;

/**
 * Created by adr on 11/28/15.
 */
@Entity
public class Person7 {
    private Integer id;
    private String name;
    private Gender gender;
    private Country7 country;
    private List<String> pseudonims = new ArrayList<>();
    private Map<String, Address7> addresses = new HashMap<>();

    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "person")
    @MapKey(name = "address")
    public Map<String, Address7> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<String, Address7> addresses) {
        this.addresses = addresses;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = {PERSIST, REFRESH, DETACH})
    public Country7 getCountry() {
        return country;
    }

    public void setCountry(Country7 country) {
        this.country = country;
    }

    @ElementCollection
    public List<String> getPseudonims() {
        return pseudonims;
    }

    public void setPseudonims(List<String> pseudonims) {
        this.pseudonims = pseudonims;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person7{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", addresses=" + addresses +
                '}';
    }
}
