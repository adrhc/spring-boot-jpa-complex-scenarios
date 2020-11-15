package test_project.entities.embeddable;

/**
 * Created by adr on 11/20/15.
 */

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProfessorId implements Serializable {
    private String country;

    @Column(name = "EMP_ID")
    private int id;

    public ProfessorId() {
    }

    public ProfessorId(String country, int id) {
        this.country = country;
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Object o) {
        return ((o instanceof ProfessorId) && country.equals(((ProfessorId) o).getCountry()) && id == ((ProfessorId) o)
                .getId());

    }

    public int hashCode() {
        return country.hashCode() + id;
    }
}