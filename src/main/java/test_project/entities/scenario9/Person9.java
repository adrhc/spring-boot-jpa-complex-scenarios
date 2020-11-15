package test_project.entities.scenario9;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by adriana on 30-Nov-15.
 */
@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Person9.messages.attachments",
                attributeNodes = {
                        @NamedAttributeNode(value = "messages", subgraph = "messages")
                },
                subgraphs = {
                        @NamedSubgraph(name = "messages", attributeNodes = {
                                @NamedAttributeNode(value = "attachments")
                        })
                }
        ),
        @NamedEntityGraph(name = "Person9.secrets",
                attributeNodes = {
                        @NamedAttributeNode(value = "secrets", subgraph = "Secret")
                },
                subgraphs = {
                        @NamedSubgraph(name = "Secret", attributeNodes = {
                                @NamedAttributeNode(value = "secret"),
                                @NamedAttributeNode(value = "code")
                        })
                }
        ),
        @NamedEntityGraph(name = "Person9.translations",
                attributeNodes = {
                        @NamedAttributeNode(value = "translations",
                                subgraph = "PersonNameTranslated", keySubgraph = "Language")
                },
                subgraphs = {
                        @NamedSubgraph(name = "Language",
                                attributeNodes = {@NamedAttributeNode(value = "name")}),
                        @NamedSubgraph(name = "PersonNameTranslated",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "translation"),
                                        @NamedAttributeNode(
                                                value = "language",
                                                subgraph = "PersonNameTranslated.Language"
                                        )
                                }),
                        @NamedSubgraph(name = "PersonNameTranslated.Language",
                                attributeNodes = {@NamedAttributeNode(value = "flag")})
                }
        )
})
public class Person9 implements Serializable {
    @Id
    @GeneratedValue
    public int id;
    public String name;
    public String surname;
    @OneToMany(mappedBy = "person", cascade = ALL, orphanRemoval = true)
    public List<EmailMessage> messages = new ArrayList<>();
    @OneToMany(mappedBy = "person", cascade = ALL, orphanRemoval = true)
    @MapKey(name = "name")
    public Map<String, Secret> secrets = new HashMap<>();
    @OneToMany(mappedBy = "person", cascade = ALL, orphanRemoval = true)
    @MapKeyJoinColumn(name = "language_id")
    public Map<Language, PersonNameTranslated> translations = new HashMap<>();

    @Override
    public String toString() {
        return "Person9{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", messages=" + messages +
                ", secrets=" + secrets +
                ", translations=" + translations +
                '}';
    }
}
