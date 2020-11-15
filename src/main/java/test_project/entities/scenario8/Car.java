package test_project.entities.scenario8;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

/**
 * Created by adriana on 30-Nov-15.
 */
@Entity
public class Car {
    private Integer id;
    private String registration;
    private int viewsCount;
    private long version;
    private Model model;
    private Owner owner;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @ManyToOne(cascade = {PERSIST, DETACH, REFRESH})
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne(cascade = ALL)
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                ", viewsCount=" + viewsCount +
                ", version=" + version +
                ", model=" + model +
                '}';
    }
}
