package test_project.entities;

import test_project.entities.scenario1.Phone1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adr on 11/9/15.
 */
@Entity
@Table(name = "T_GEEK")
public class Geek extends Person {
    private String favouriteProgrammingLanguage;
    private List<Project> projects = new ArrayList<>();
    private List<Phone1> phone1s;

    @Column(name = "FAV_PROG_LANG")
    public String getFavouriteProgrammingLanguage() {
        return favouriteProgrammingLanguage;
    }

    public void setFavouriteProgrammingLanguage(String favouriteProgrammingLanguage) {
        this.favouriteProgrammingLanguage = favouriteProgrammingLanguage;
    }

    @ManyToMany
    @JoinTable(
            name = "T_GEEK_PROJECT",
            joinColumns = {@JoinColumn(name = "GEEK_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID")})
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @OneToMany(mappedBy = "geek", fetch = FetchType.EAGER)
    public List<Phone1> getPhone1s() {
        return phone1s;
    }

    public void setPhone1s(List<Phone1> phone1s) {
        this.phone1s = phone1s;
    }

    @Override
    public String toString() {
        return super.toString() + "\nGeek{" +
                "favouriteProgrammingLanguage='" + favouriteProgrammingLanguage + '\'' +
                ", projects=" + projects +
                ", phone1s=" + phone1s +
                '}';
    }
}