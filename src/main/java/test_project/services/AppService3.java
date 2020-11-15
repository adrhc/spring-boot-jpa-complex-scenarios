package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_project.entities.scenario3.Department3;
import test_project.entities.scenario3.Person3;
import test_project.entities.scenario3.PrintJob;
import test_project.entities.scenario3.PrintQueue;

import javax.persistence.EntityManager;

/**
 * Created by adr on 11/22/15.
 */
@Service
@Transactional
public class AppService3 {
    private static final Logger logger = LoggerFactory.getLogger(AppService3.class);

    @Autowired
    private EntityManager em;

    public void doOneToManyWithJoinTable() {
        logger.debug("@OneToMany using @JoinTable");
        logger.debug("Person3 cascades to Department3");
        Department3 d = new Department3();
        d.setName("Design");

        Person3 p1 = new Person3("Tom");
        // 1. really necessary in order to persist department-fk because using @JoinTable
        p1.setDepartment3(d);

        Person3 p2 = new Person3("Jack");
        // 1. really necessary in order to persist department-fk because using @JoinTable
        p2.setDepartment3(d);

        // 1. really necessary in order to persist person-fk because using @JoinTable
        d.getPerson3s().add(p1);
        d.getPerson3s().add(p2);

        em.persist(p1);
        em.persist(p2);

        // 1. really necessary in order to persist Department3 because not using cascade
        em.persist(d);
    }

    public void showDepartment3() {
        logger.debug("@OneToMany using @JoinTable - showDepartment3");
        Department3 d = em.find(Department3.class, 1L);
        logger.debug(d.toString());
    }

    public void doOneToManyWithOrderColumn() {
        logger.debug("@OneToMany using @OrderColumn");
        PrintQueue q = new PrintQueue();
        q.setName("q1");

        PrintJob j1 = new PrintJob("j1");
        j1.setQueue(q);
        PrintJob j2 = new PrintJob("j2");
        j2.setQueue(q);
        PrintJob j3 = new PrintJob("j3");
        j3.setQueue(q);

        // 1. really necessary in order to persist @OrderColumn
        // the job ids won't be ordered but the jobs list will be
        q.getJobs().add(j1);
        q.getJobs().add(j2);
        q.getJobs().add(j3);

        // after all persists an update for every PrintJob will set the correct @OrderColumn
        em.persist(j3);
        em.persist(j1);
        em.persist(q);
        em.persist(j2);
    }

    public void showPrintQueue() {
        logger.debug("@OneToMany using @OrderColumn - showPrintQueue");
        PrintQueue q = em.find(PrintQueue.class, 1);
        logger.debug(q.toString());
        for (PrintJob j : q.getJobs()) {
            logger.debug(j.toString());
        }
    }
}
