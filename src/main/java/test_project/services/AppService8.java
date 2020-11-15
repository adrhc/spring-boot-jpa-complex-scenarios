package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_project.entities.scenario8.Car;
import test_project.entities.scenario8.Car_;
import test_project.entities.scenario8.Model;
import test_project.entities.scenario8.Owner;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by adr on 11/22/15.
 */
@Service
@Transactional
public class AppService8 {
    private static final Logger logger = LoggerFactory.getLogger(AppService8.class);

    @Autowired
    private ChangeCarCreationDateRunnable changeCarCreationDateRunnable;
    @Autowired
    private EntityManager em;
    private CriteriaBuilder cb;

    public void createCars() {
        logger.debug("begin");
        Car car = new Car();
        car.setRegistration("b30 jak");
        Owner owner = new Owner();
        owner.setName("gigi");
        car.setOwner(owner);
        Model model = new Model();
        model.setName("model1");
        car.setModel(model);
        em.persist(car);
    }

    public void findCarWithOptimisticLock() {
        logger.debug("begin");
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        cq.from(Car.class);
        TypedQuery<Car> tq = em.createQuery(cq);
        tq.setLockMode(LockModeType.OPTIMISTIC);// requires @Version on Car
        List<Car> cars = tq.getResultList();
        for (Car c : cars) {
            logger.debug(c.toString());
        }
    }

    public void findOwnerWithOptimisticLock() {
        logger.debug("begin");
        CriteriaQuery<Owner> cq = cb.createQuery(Owner.class);
        Root<Car> car = cq.from(Car.class);
        Join<Car, Owner> owner = car.join(Car_.owner);
        cq.select(owner);// determines who must use @Version
        TypedQuery<Owner> tq = em.createQuery(cq);
        tq.setLockMode(LockModeType.OPTIMISTIC);// requires @Version on Owner
        List<Owner> owners = tq.getResultList();
        for (Owner o : owners) {
            logger.debug(o.toString());
        }
    }

    public void changeCarCreationDate() {
        logger.debug("begin");
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        cq.from(Car.class);
        TypedQuery<Car> tq = em.createQuery(cq);
        List<Car> cars = tq.getResultList();
        for (Car car : cars) {
            car.setViewsCount(car.getViewsCount() + 1);
            logger.debug(car.toString());
        }
        logger.debug("end");
    }

    public void findWithOptimisticLockWhileChanged() {
        logger.debug("begin");
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        cq.from(Car.class);
        TypedQuery<Car> tq = em.createQuery(cq);
        tq.setLockMode(LockModeType.OPTIMISTIC);
        logger.debug(tq.getSingleResult().toString());
        changeCarCreationDateRunnable.runChangeCarCreationDate();
        logger.debug("end");
    }

    public void nonVersionedPessimisticReadLock() {
        logger.debug("begin");
        CriteriaQuery<Model> cq = cb.createQuery(Model.class);
        cq.from(Model.class);
        TypedQuery<Model> tq = em.createQuery(cq);
        tq.setLockMode(LockModeType.PESSIMISTIC_READ);// works also without @Version on target Entity
        List<Model> models = tq.getResultList();
        for (Model m : models) {
            logger.debug(m.toString());
        }
    }

    public void versionedPessimisticReadLock() {
        logger.debug("begin");
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        cq.from(Car.class);
        TypedQuery<Car> tq = em.createQuery(cq);
        tq.setLockMode(LockModeType.PESSIMISTIC_READ);
        List<Car> cars = tq.getResultList();
        for (Car c : cars) {
            logger.debug(c.toString());
        }
    }

    public void versionedPessimisticReadLockWhileChanged() {
        logger.debug("begin");
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        cq.from(Car.class);
        TypedQuery<Car> tq = em.createQuery(cq);
        tq.setLockMode(LockModeType.PESSIMISTIC_READ);
        List<Car> cars = tq.getResultList();
        for (Car c : cars) {
            logger.debug(c.toString());
        }
        // Timeout trying to lock table
        changeCarCreationDateRunnable.runChangeCarCreationDate();
    }

    public void versionedPessimisticForceIncrementLock() {
        logger.debug("begin");
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        cq.from(Car.class);
        TypedQuery<Car> tq = em.createQuery(cq);
        tq.setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT);// requires @Version on Car
        List<Car> cars = tq.getResultList();
        for (Car c : cars) {
            logger.debug(c.toString());
        }
    }

    @PostConstruct
    protected void postConstruct() {
        cb = em.getCriteriaBuilder();
    }
}
