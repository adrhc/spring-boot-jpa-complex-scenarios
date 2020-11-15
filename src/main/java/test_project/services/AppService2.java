package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_project.entities.scenario2.*;

import javax.persistence.EntityManager;

/**
 * Created by adr on 11/22/15.
 */
@Service
@Transactional
public class AppService2 {
    private static final Logger logger = LoggerFactory.getLogger(AppService2.class);

    @Autowired
    private EntityManager em;

    public void savePersonWithDepartmentJoinedOnPK() {
        logger.debug("@OneToOne with @PrimaryKeyJoinColumn");
        Person22 person = new Person22();
        person.setName("person");
        DepartmentY departmenty = new DepartmentY();
        departmenty.setName("departmenty");
        person.setDepartmenty(departmenty);
        departmenty.setPerson(person);
        em.persist(departmenty);
        em.persist(person);
    }

    public void savePersonWithDepartmentJoinedOkFK() {
        logger.debug("@OneToOne with @JoinColumn");
        Department2 department2 = new Department2();
        department2.setName("department");
        Person21 person21 = new Person21();
        person21.setName("person");
        person21.setDepartment2(department2);
        em.persist(department2);
        em.persist(person21);
    }

    public void saveDepartmentZ() {
        logger.debug("@OneToOne with @JoinColumn + departmentz cascade on personz");
        DepartmentZ departmentz = new DepartmentZ();
        departmentz.setName("departmentz");
        PersonZ personz = new PersonZ();
        personz.setName("personz");
        departmentz.setPersonz(personz);
        em.persist(departmentz);
    }

    public void showPerson(Long id) {
        logger.debug("showPerson {}", id);
        Person21 p = em.find(Person21.class, id);
        // because using @JoinColumn(name = "PERS_DEPT_ID2") in Person
        // than a secondary select is made with the only difference being the where clause:
        // where person2.pers_dept_id2=?
        // The where clause for the person-select is:
        // person2.id = ?
        // The secondary select does not happens when using @PrimaryKeyJoinColum!
        logger.debug(p.toString());
    }

    public void showPerson3(Long id) {
        logger.debug("showPerson3 {}", id);
        // using @PrimaryKeyJoinColum ...
        Person22 p = em.find(Person22.class, id);
        logger.debug(p.toString());
    }

    public void showPersonZ(Long id) {
        PersonZ p = em.find(PersonZ.class, id);
        logger.debug(p.toString());
    }
}
