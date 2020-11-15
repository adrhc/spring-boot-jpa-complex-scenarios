package test_project.entities;

import test_project.entities.converter.BooleanConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by adr on 11/9/15.
 */
@Entity
@Table(name = "T_ID_CARD")
public class IdCard implements Serializable {
    private Long id;
    private String idNumber;
    private Date issueDate;
    private boolean valid;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ID_NUMBER")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Column(name = "ISSUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Column(name = "VALID")
    @Convert(converter = BooleanConverter.class)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "IdCard{" +
                "id=" + id +
                ", idNumber='" + idNumber + '\'' +
                ", issueDate=" + issueDate +
                ", valid=" + valid +
                '}';
    }
}