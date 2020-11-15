package test_project.entities.scenario9;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by adriana on 30-Nov-15.
 */
@Entity
public class EmailAttachment implements Serializable {
    @Id
    @GeneratedValue
    public int id;
    public String text;
    @ManyToOne(fetch = FetchType.LAZY)
    public EmailMessage message;

    @Override
    public String toString() {
        return "EmailAttachment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
