package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_project.entities.scenario6.Address6;
import test_project.entities.scenario6.Person6;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adr on 11/22/15.
 */
@Service
@Transactional
public class AppService6 {
    private static final Logger logger = LoggerFactory.getLogger(AppService6.class);

    @Autowired
    private EntityManager em;

    public void createPersonWithMapKey() {
        logger.debug("@OneToMany + @MapKey");
        Person6 person6 = new Person6();
        person6.setName("gigi");
        Address6 address61 = new Address6();
        address61.setAddress("location1");
        address61.setPerson(person6);
        Address6 address62 = new Address6();
        address62.setAddress("location2");
        address62.setPerson(person6);
        person6.getAddresses().put(address61.getAddress(), address61);
        person6.getAddresses().put(address62.getAddress(), address62);
        em.persist(person6);
    }

    public void showPerson6() {
        logger.debug("showPerson6");
        logger.debug("see https://adrhc.go.ro/wordpress/mapkey-vs-mapkeycolumn");
        Person6 person6 = em.find(Person6.class, 1);
        logger.debug(person6.toString());
    }

    public void showPerson6WithEntityGraph() {
        logger.debug("showPerson6WithEntityGraph");
        EntityGraph person6Address = this.em.getEntityGraph("Person6.addresses");
        Map<String, Object> hints = new HashMap<>(1, 1);
        hints.put("javax.persistence.fetchgraph", person6Address);
        Person6 person6 = em.find(Person6.class, 1, hints);
        logger.debug(person6.toString());
    }

    public void showPerson6WithProgramaticEntityGraph() {
        logger.debug("showPerson6WithProgramaticEntityGraph");
        EntityGraph<Person6> person6Address = this.em.createEntityGraph(Person6.class);
        person6Address.addAttributeNodes("addresses");
        // Below works too but in addition (comparing to addAttributeNodes) creates a
        // Subgraph<Address6> allowing for further attributes & subgraphs to be added:
//        Subgraph<Address6> address6Subgraph = person6Address.addSubgraph("address");
        Map<String, Object> hints = new HashMap<>(1, 1);
        hints.put("javax.persistence.fetchgraph", person6Address);
        Person6 person6 = em.find(Person6.class, 1, hints);
        logger.debug(person6.toString());
    }

    public void checkPersonAddress1() {
        logger.debug("checkPersonAddress IN(p.addresses)");
        // don't omit "SELECT p" otherwise will throw an error saying:
        // "Cannot create TypedQuery for query with more than one return using
        // requested result type [test_project.entities.scenario6.Person7]"
        TypedQuery<Person6> query = em.createQuery(
                "SELECT p from Person6 p, IN(p.addresses) a where a = :address", Person6.class);
        Address6 address6 = new Address6();
        address6.setId(1);
        query.setParameter("address", address6);
        Person6 person6 = query.getSingleResult();
        logger.debug(person6.toString());
    }

    public void checkPersonAddress2() {
        logger.debug("checkPersonAddress JOIN p.addresses");
        // don't omit "SELECT p" otherwise will throw an error saying:
        // "Cannot create TypedQuery for query with more than one return using
        // requested result type [test_project.entities.scenario6.Person7]"
        TypedQuery<Person6> query = em.createQuery(
                "SELECT p from Person6 p JOIN p.addresses a where a = :address", Person6.class);
        Address6 address6 = new Address6();
        address6.setId(1);
        query.setParameter("address", address6);
        Person6 person6 = query.getSingleResult();
        logger.debug(person6.toString());
    }

    public void checkPersonAddresses() {
        logger.debug("checkPersonAddress 'SELECT p' omitted ...");
        TypedQuery<Object[]> query = em.createQuery(
                "from Person6 p, IN(p.addresses) a", Object[].class);
        List<Object[]> person6 = query.getResultList();
        for (Object[] p : person6) {
            logger.debug(p[0].toString());// person
            logger.debug(p[1].toString());// address
        }
    }
}
