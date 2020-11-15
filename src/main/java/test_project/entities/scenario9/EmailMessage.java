package test_project.entities.scenario9;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

/**
 * Created by adriana on 30-Nov-15.
 */
@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "EmailMessage.sender+subject", attributeNodes = {
                @NamedAttributeNode(value = "sender"),
                @NamedAttributeNode(value = "subject")
        }),
        @NamedEntityGraph(name = "EmailMessage.attachments", attributeNodes = {
                @NamedAttributeNode(value = "sender"),
                @NamedAttributeNode(value = "subject"),
                @NamedAttributeNode(value = "attachments")
        }),
})
public class EmailMessage implements Serializable {
    @Id
    @GeneratedValue
    public int id;
    @Basic(fetch = EAGER)
    public String sender;
    @Basic(fetch = EAGER)
    public String subject;
    @Basic(fetch = LAZY)
    public String body;
    @OneToMany(mappedBy = "message", cascade = ALL, orphanRemoval = true)
    public Set<EmailAttachment> attachments = new HashSet<>();
    @ManyToOne(optional = false, fetch = LAZY)
    public Person9 person;

    @Override
    public String toString() {
        return "EmailMessage{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", sender='" + sender + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
