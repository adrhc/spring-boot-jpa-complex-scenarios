package test_project.entities.scenario1;

import test_project.entities.Geek;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by adr on 11/9/15.
 */
@Entity
@Table(name = "T_PHONE1")
public class Phone1 implements Serializable {
    private Long id;
    private String number;
    private Geek geek;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NUMBER")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GEEK_ID")
    public Geek getGeek() {
        return geek;
    }

    public void setGeek(Geek geek) {
        this.geek = geek;
    }

    @Override
    public String toString() {
        return "Phone1{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}