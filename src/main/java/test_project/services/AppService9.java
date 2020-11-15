package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import test_project.entities.scenario9.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adr on 11/22/15.
 */
@Service
@Transactional
public class AppService9 {
    private static final Logger logger = LoggerFactory.getLogger(AppService9.class);

    @Autowired
    private ApplicationContext ac;
    @Autowired
    private EntityManager em;
    private CriteriaBuilder cb;

    public void createEmailMessage() {
        logger.debug("begin");
        Person9 person9 = new Person9();
        person9.name = "person9";
        person9.surname = "surname9";
        Language language = new Language();
        language.name = "language1";
        language.code = "code1";
        language.flag = "flag1";
        PersonNameTranslated nameTranslated = new PersonNameTranslated();
        nameTranslated.translation = "translation1";
        nameTranslated.person = person9;
        nameTranslated.language = language;
        person9.translations.put(nameTranslated.language, nameTranslated);
        Secret secret = new Secret();
        secret.name = "name1";
        secret.secret = "secret1";
        secret.code = "code1";
        secret.person = person9;
        person9.secrets.put(secret.secret, secret);
        EmailMessage message = new EmailMessage();
        message.body = "body1";
        message.sender = "sender1";
        message.subject = "subject1";
        message.person = person9;
        person9.messages.add(message);
        EmailAttachment attachment = new EmailAttachment();
        attachment.text = "text1";
        attachment.message = message;
        message.attachments.add(attachment);
        em.persist(person9);
    }

    public void showEmailMessages() {
        logger.debug("begin using EntityGraph named EmailMessage.sender+subject");
        logger.debug("should only take EmailMessage.sender and EmailMessage.subject " +
                "but instead it wrongly loads all columns)");
        CriteriaQuery<EmailMessage> cq = cb.createQuery(EmailMessage.class);
        cq.from(EmailMessage.class);
        TypedQuery<EmailMessage> tq = em.createQuery(cq);
        EntityGraph eg = em.getEntityGraph("EmailMessage.sender+subject");
        tq.setHint("javax.persistence.fetchgraph", eg);
        List<EmailMessage> msgs = tq.getResultList();
        logger.debug("msgs: {}", msgs.size());
        for (EmailMessage m : msgs) {
            logger.debug("sender: {}, subject: {}", m.sender, m.subject);
        }
    }

    public void emailMessageSenderSubjectAttachments() {
        logger.debug("begin using EntityGraph named EmailMessage.attachments");
        CriteriaQuery<EmailMessage> cq = cb.createQuery(EmailMessage.class);
        cq.from(EmailMessage.class);
        TypedQuery<EmailMessage> tq = em.createQuery(cq);
        EntityGraph eg = em.getEntityGraph("EmailMessage.attachments");
        tq.setHint("javax.persistence.fetchgraph", eg);
        List<EmailMessage> msgs = tq.getResultList();
        logger.debug("msgs: {}", msgs.size());
        for (EmailMessage m : msgs) {
            logger.debug("sender: {}, subject: {}", m.sender, m.subject);
            logger.debug(m.attachments.toString());
        }
    }

    public void emailMessageBodyAttachments() {
        logger.debug("begin using programmatic EntityGraph (same as EmailMessage.attachments)");
        EntityGraph<EmailMessage> eg = em.createEntityGraph(EmailMessage.class);
        eg.addAttributeNodes(EmailMessage_.body);
        eg.addAttributeNodes(EmailMessage_.attachments);
        Map<String, Object> props = new HashMap<>();
        props.put("javax.persistence.fetchgraph", eg);
        EmailMessage message = em.find(EmailMessage.class, 1, props);
        logger.debug("body: {}", message.body);
        logger.debug(message.attachments.toString());
    }

    public void person9MessagesAttachments() {
        logger.debug("begin using EntityGraph named Person9.messages.attachments");
        CriteriaQuery<Person9> cq = cb.createQuery(Person9.class);
        cq.from(Person9.class);
        TypedQuery<Person9> tq = em.createQuery(cq);
        EntityGraph eg = em.getEntityGraph("Person9.messages.attachments");
        tq.setHint("javax.persistence.fetchgraph", eg);
        List<Person9> pers = tq.getResultList();
        logger.debug("pers: {}", pers.size());
        for (Person9 p : pers) {
            logger.debug(p.messages.toString());
        }
    }

    public void person9Secrets() {
        logger.debug("begin using EntityGraph named Person9.secrets (Map<String, Secret>)");
        CriteriaQuery<Person9> cq = cb.createQuery(Person9.class);
        cq.from(Person9.class);
        TypedQuery<Person9> tq = em.createQuery(cq);
        EntityGraph eg = em.getEntityGraph("Person9.secrets");
        tq.setHint("javax.persistence.fetchgraph", eg);
        List<Person9> pers = tq.getResultList();
        logger.debug("pers: {}", pers.size());
        for (Person9 p : pers) {
            logger.debug(p.secrets.toString());
        }
    }

    public void person9Translations() {
        logger.debug("begin using EntityGraph named Person9.translations (Map<Language, PersonNameTranslated>)");
        CriteriaQuery<Person9> cq = cb.createQuery(Person9.class);
        cq.from(Person9.class);
        TypedQuery<Person9> tq = em.createQuery(cq);
        EntityGraph eg = em.getEntityGraph("Person9.translations");
        tq.setHint("javax.persistence.fetchgraph", eg);
        List<Person9> pers = tq.getResultList();
        logger.debug("pers: {}", pers.size());
        for (Person9 p : pers) {
            logger.debug(p.translations.toString());
        }
    }

    @Transactional(propagation = Propagation.NEVER)
    public void persistWithExistingPersonWrapper() {
        try {
            ac.getBean(AppService9.class).persistWithExistingPerson();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void persistWithExistingPerson() {
        CriteriaQuery<Person9> cq = cb.createQuery(Person9.class);
        cq.from(Person9.class);
        TypedQuery<Person9> tq = em.createQuery(cq);
        Person9 p = tq.getSingleResult();
        em.detach(p);
        Person9 person9 = new Person9();
        person9.id = p.id;
        person9.name = "person9b";
        person9.surname = "surname9b";
        em.persist(person9);// fails
    }

    @PostConstruct
    protected void postConstruct() {
        cb = em.getCriteriaBuilder();
    }
}
