package test_project.entities.scenario9;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by adriana on 01-Dec-15.
 */
@Entity
public class Language {
    @Id
    @GeneratedValue
    public int id;
    public String name;
    public String code;
    public String flag;

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
