package test_project.entities.scenario9;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

/**
 * Created by adriana on 01-Dec-15.
 */
@Entity
public class PersonNameTranslated {
    @Id
    @GeneratedValue
    public int id;
    public String translation;
    @ManyToOne(fetch = LAZY, optional = false)
    public Person9 person;
    @OneToOne(fetch = LAZY, cascade = {PERSIST, REFRESH, DETACH}, optional = false)
    public Language language;

    @Override
    public String toString() {
        return "PersonNameTranslated{" +
                "id=" + id +
                ", translation='" + translation + '\'' +
                ", language=" + language +
                '}';
    }
}
