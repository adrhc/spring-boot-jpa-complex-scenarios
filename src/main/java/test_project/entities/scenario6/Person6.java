package test_project.entities.scenario6;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adr on 11/28/15.
 */
@Entity
@NamedEntityGraph(name = "Person6.addresses",
        attributeNodes = @NamedAttributeNode(value = "addresses"))
public class Person6 {
    private Integer id;
    private String name;
    private Map<String, Address6> addresses = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "person")
    @MapKey(name = "address")
    public Map<String, Address6> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<String, Address6> addresses) {
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

    @Override
    public String toString() {
        return "Person7{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
