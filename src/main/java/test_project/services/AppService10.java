package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_project.entities.scenario9.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by adr on 11/22/15.
 */
@Service
@Transactional
public class AppService10 {
    private static final Logger logger = LoggerFactory.getLogger(AppService10.class);

    @Autowired
    private EntityManager em;
    private CriteriaBuilder cb;

    public void queryPerson9WithTuple() {
        logger.debug("learn: tuple");
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Person9> person9 = cq.from(Person9.class);
        cq.multiselect(person9.get(Person9_.name), person9.get(Person9_.surname));
        TypedQuery<Tuple> q = em.createQuery(cq);
        List<Tuple> list = q.getResultList();
        for (Tuple t : list) {
            logger.debug("person: {}", t.get(person9.get(Person9_.name)));
            logger.debug("{}", t.toArray());
        }
    }

    public void searchPerson9WithFetch() {
        logger.debug("learn: fetch");
        CriteriaQuery<Person9> cq = cb.createQuery(Person9.class);
        Root<Person9> person9 = cq.from(Person9.class);
        person9.fetch(Person9_.translations);
        cq.select(person9);
        TypedQuery<Person9> q = em.createQuery(cq);
        List<Person9> list = q.getResultList();
        for (Person9 p : list) {
            logger.debug("{} {}:", p.name, p.surname);
            logger.debug("{}", p.translations);
        }
    }

    public void searchPerson9WithFetchMapKey() {
        logger.debug("learn: fetch-join, fetch map-key");
        CriteriaQuery<Person9> cq = cb.createQuery(Person9.class);
        Root<Person9> person9 = cq.from(Person9.class);
        // this is a fetch-join
        Fetch<Person9, PersonNameTranslated> translationsFetch = person9.fetch(Person9_.translations);
        // now fetches the map-key
        translationsFetch.fetch(PersonNameTranslated_.language);
        // won't let you fetch the map-key when the join is NOT a fetch-join (as below)
//        MapJoin<Person9, Language, PersonNameTranslated> translationsJoin = person9.join(Person9_.translations);
//        translationsJoin.fetch(PersonNameTranslated_.language);
        cq.select(person9);
        TypedQuery<Person9> q = em.createQuery(cq);
        List<Person9> list = q.getResultList();
        for (Person9 p : list) {
            logger.debug("{} {}:", p.name, p.surname);
            logger.debug("{}", p.translations);
        }
    }

    public void searchPerson9WWhereExistsAttachTextLengthLess10() {
        logger.debug("learn: correlate + exists + jpa-FUNCTION");
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<Person9> person9 = cq.from(Person9.class);
        Path<String> name = person9.get(Person9_.name);
        Expression<String> function = cb.function("REPEAT", String.class,
                person9.get(Person9_.name), cb.literal(3));
        cq.multiselect(name, function);

        Subquery<Integer> exists = cq.subquery(Integer.class);
        Root<EmailAttachment> attach = exists.from(EmailAttachment.class);
        ListJoin<Person9, EmailMessage> messagesJoin = person9.join(Person9_.messages);
        exists.correlate(messagesJoin);
        exists.where(cb.lessThan(cb.length(attach.get(EmailAttachment_.text)), 10));
        exists.select(cb.literal(1));
        cq.where(cb.exists(exists));

        TypedQuery<Tuple> q = em.createQuery(cq);
        List<Tuple> list = q.getResultList();
        for (Tuple t : list) {
            logger.debug("LPAD {}: {}", t.get(name), t.get(function));
        }
    }

    @PostConstruct
    protected void postConstruct() {
        cb = em.getCriteriaBuilder();
    }
}
