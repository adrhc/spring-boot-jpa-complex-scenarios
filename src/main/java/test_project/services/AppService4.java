package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_project.entities.scenario4.Department4;
import test_project.entities.scenario4.Person4;

import javax.persistence.EntityManager;

/**
 * Created by adr on 11/22/15.
 */
@Service
@Transactional
public class AppService4 {
    private static final Logger logger = LoggerFactory.getLogger(AppService4.class);

    @Autowired
    private EntityManager em;

    public void doOneToManyMapKey() {
        logger.debug("@OneToMany + @MapKey");
        // The HashMap backing the Department4.person4s HashSet
        // will use as map-key the @MapKey specified property.

        Department4 d = new Department4();
        d.setName("Design");

        Person4 p1 = new Person4("Tom");
        p1.setDepartment4(d);

        Person4 p2 = new Person4("Jack");
        p2.setDepartment4(d);

        // 1. not really necessary in order to persist everything
//        d.getPerson4s().add(p1);
//        d.getPerson4s().add(p2);

        em.persist(d);
        em.persist(p1);
        em.persist(p2);
        // if department is persisted last than an update shall occur for every
        // person persisted and referencing it in order to set the department FK!
    }
}
