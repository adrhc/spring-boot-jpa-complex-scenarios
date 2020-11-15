package test_project.entities;

import test_project.entities.embeddable.Period;
import test_project.enums.ProjectType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adr on 11/14/15.
 */
@Entity
@Table(name = "T_PROJECT")
public class Project implements Serializable {
    private Long id;
    private String title;
    private List<Geek> geeks = new ArrayList<>();
    private Period projectPeriod;
    private List<Period> billingPeriods = new ArrayList<>();
    private ProjectType projectType;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToMany(mappedBy = "projects")
    public List<Geek> getGeeks() {
        return geeks;
    }

    public void setGeeks(List<Geek> geeks) {
        this.geeks = geeks;
    }

    @Embedded
    public Period getProjectPeriod() {
        return projectPeriod;
    }

    public void setProjectPeriod(Period projectPeriod) {
        this.projectPeriod = projectPeriod;
    }

    @ElementCollection
    @CollectionTable(
            name = "T_BILLING_PERIOD",
            joinColumns = @JoinColumn(name = "PROJECT_ID")
    )
    public List<Period> getBillingPeriods() {
        return billingPeriods;
    }

    public void setBillingPeriods(List<Period> billingPeriods) {
        this.billingPeriods = billingPeriods;
    }

    @Enumerated(EnumType.STRING)
    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", projectPeriod=" + projectPeriod +
                ", billingPeriods=" + billingPeriods +
                ", projectType=" + projectType +
                '}';
    }
}