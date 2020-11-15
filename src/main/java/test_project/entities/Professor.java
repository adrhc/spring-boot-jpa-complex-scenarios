package test_project.entities;

/**
 * Created by adr on 11/20/15.
 */

import test_project.entities.embeddable.ProfessorId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Professor {
    @EmbeddedId
    private ProfessorId id;

    private String name;

    private long salary;

    public Professor() {
    }

    public Professor(String country, int id) {
        this.id = new ProfessorId(country, id);
    }

    public int getId() {
        return id.getId();
    }

    public String getCountry() {
        return id.getCountry();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String toString() {
        return "Professor id: " + getId() + " name: " + getName() + " country: " + getCountry();
    }
}