package test_project.entities.scenario9;

import javax.persistence.*;

/**
 * Created by adriana on 30-Nov-15.
 */
@Entity
public class Secret {
    @Id
    @GeneratedValue
    public int id;
    public String name;
    public String secret;
    public String code;
    @ManyToOne(fetch = FetchType.LAZY)
    public Person9 person;

    @Override
    public String toString() {
        return "Secret{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
