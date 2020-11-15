package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_project.dto.PersonAndCountryName;
import test_project.entities.scenario7.*;
import test_project.enums.Gender;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
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
public class AppService7 {
    private static final Logger logger = LoggerFactory.getLogger(AppService7.class);

    @Autowired
    private EntityManager em;

    public void createPerson1() {
        logger.debug("begin");
        // person
        Person7 person = new Person7();
        person.setName("gigi");
        person.setGender(Gender.M);
        person.getPseudonims().add("pseudonim-gigi1");
        person.getPseudonims().add("pseudonim-gigi2");
        // addresses
        Address7 address1 = new Address7();
        address1.setAddress("location-gigi1");
        address1.setPerson(person);
        Address7 address2 = new Address7();
        address2.setAddress("location-gigi2");
        address2.setPerson(person);
        // person's addresses
        person.getAddresses().put(address1.getAddress(), address1);
        person.getAddresses().put(address2.getAddress(), address2);
        // country
        Country7 country = new Country7();
        country.setName("country-gigi");
        // person's country
        person.setCountry(country);
        em.persist(person);
    }

    public void createPerson2() {
        logger.debug("begin");
        // person
        Person7 person = new Person7();
        person.setName("kent");
        person.setGender(Gender.M);
        person.getPseudonims().add("pseudonim-kent1");
        person.getPseudonims().add("pseudonim-kent2");
        // addresses
        Address7 address1 = new Address7();
        address1.setAddress("location-kent3");
        address1.setPerson(person);
        Address7 address2 = new Address7();
        address2.setAddress("location-kent4");
        address2.setPerson(person);
        // person's addresses
        person.getAddresses().put(address1.getAddress(), address1);
        person.getAddresses().put(address2.getAddress(), address2);
        // country
        Country7 country = new Country7();
        country.setName("country-kent");
        // person's country
        person.setCountry(country);
        em.persist(person);
    }

    public void showPersons() {
        logger.debug("begin");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person7> cq = cb.createQuery(Person7.class);
        Root<Person7> root = cq.from(Person7.class);
        cq.select(root);
        TypedQuery<Person7> tq = em.createQuery(cq);
        List<Person7> persons = tq.getResultList();
        for (Person7 p : persons) {
            logger.debug(p.toString());
        }
    }

    public void showPersonsName() {
        logger.debug("StaticMetamodel: Person7 select");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Person7> root = cq.from(Person7.class);
        cq.select(root.get(Person7_.name));
        TypedQuery<String> tq = em.createQuery(cq);
        List<String> persons = tq.getResultList();
        for (String p : persons) {
            logger.debug(p);
        }
    }

    public void showPersonsNameAndCountry() {
        logger.debug("StaticMetamodel: Person7_.name, Country7_.name");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Person7> root = cq.from(Person7.class);
        Join<Person7, Country7> personCountry = root.join(Person7_.country);// working example1
        cq.multiselect(root.get(Person7_.name), personCountry.get(Country7_.name));// working example1
//        cq.multiselect(root.get(Person7_.name), root.get(Person7_.country));// working example2
        TypedQuery<Object[]> tq = em.createQuery(cq);
        List<Object[]> results = tq.getResultList();
        for (Object[] cols : results) {
            logger.debug("person: {}, country: {}", cols[0], cols[1]);
        }
    }

    public void showPersonsNameAndCountryWithTuple() {
        logger.debug("StaticMetamodel + Tuple: Person7_.name, Country7_.name");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Person7> root = cq.from(Person7.class);
        Join<Person7, Country7> personCountry = root.join(Person7_.country);// working example1
        cq.multiselect(root.get(Person7_.name), personCountry.get(Country7_.name));// working example1
        TypedQuery<Tuple> tq = em.createQuery(cq);
        List<Tuple> results = tq.getResultList();
        for (Tuple cols : results) {
            logger.debug("person: {}, country: {}",
                    cols.get(root.get(Person7_.name)),
                    cols.get(personCountry.get(Country7_.name)));
        }
    }

    public void showPersonsNameAndCountryWithSelectNewClass() {
        logger.debug("StaticMetamodel + new PersonAndCountryName(Person7_.name, Country7_.name)");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PersonAndCountryName> cq = cb.createQuery(PersonAndCountryName.class);
        Root<Person7> root = cq.from(Person7.class);
        Join<Person7, Country7> personCountry = root.join(Person7_.country);// working example1
        cq.multiselect(root.get(Person7_.name), personCountry.get(Country7_.name));// working example1
        TypedQuery<PersonAndCountryName> tq = em.createQuery(cq);
        List<PersonAndCountryName> results = tq.getResultList();
        for (PersonAndCountryName personAndCountryName : results) {
            logger.debug(personAndCountryName.toString());
        }
    }

    public void showPersonsNameAndPseudonims() {
        logger.debug("StaticMetamodel: Person7_.name, pseudonims");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Person7> root = cq.from(Person7.class);
        Join<Person7, String> personPseudonims = root.join(Person7_.pseudonims);
        cq.multiselect(root.get(Person7_.name), personPseudonims);
        TypedQuery<Object[]> tq = em.createQuery(cq);
        List<Object[]> results = tq.getResultList();
        for (Object[] cols : results) {
            logger.debug("person: {}, pseudonim: {}", cols[0], cols[1]);
        }
        logger.debug("jpql: Person7_.name, pseudonims");
        TypedQuery<Object[]> tq1 = em.createQuery(
                "select p.name, pp from Person7 p, IN(p.pseudonims) pp", Object[].class);
        List<Object[]> results1 = tq1.getResultList();
        for (Object[] cols : results1) {
            logger.debug("person: {}, pseudonim: {}", cols[0], cols[1]);
        }
    }

    public void showOrderedAddressesLikePattern() {
        logger.debug("StaticMetamodel: where Address7_.address like 'locat%' + ordering");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Address7> cq = cb.createQuery(Address7.class);
        Root<Address7> root = cq.from(Address7.class);
//        cq.select(root);// not really mandatory when selecting everything in 'from' & 'join'!
        cq.where(cb.like(root.get(Address7_.address), "locat%"));
        cq.orderBy(cb.desc(root.get(Address7_.address)));
        TypedQuery<Address7> tq = em.createQuery(cq);
        List<Address7> adresses = tq.getResultList();
        for (Address7 a : adresses) {
            logger.debug(a.toString());
        }
    }

    public void showPersonGroupedByGender() {
        logger.debug("begin");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Person7> root = cq.from(Person7.class);
        cq.multiselect(root.get(Person7_.gender), cb.count(root));
        cq.groupBy(root.get(Person7_.gender));
        cq.having(cb.in(root.get(Person7_.gender)).value(Gender.M));
        TypedQuery<Object[]> tq = em.createQuery(cq);
        List<Object[]> persons = tq.getResultList();
        for (Object[] cols : persons) {
            logger.debug("gender: {}, count: {}", cols[0], cols[1]);
        }
    }

    public void createPets() {
        logger.debug("begin");
        Pet7 p1 = new Pet7();
        p1.setName("pet1");
        p1.setColor("red");
        Pet7 p2 = new Pet7();
        p2.setName("pet2");
        p2.setColor("red");
        em.persist(p1);
//        em.persist(p2);// showPetGroupedByColor works only with 0 or 1 pet!
    }

    public void showPetGroupedByColor() {
        logger.debug("begin - works only with 0 or 1 pet!");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pet7> cq = cb.createQuery(Pet7.class);
        Root<Pet7> root = cq.from(Pet7.class);
        cq.groupBy(root.get(Pet7_.color));
        cq.having(cb.in(root.get(Pet7_.color)).value("red"));
        TypedQuery<Pet7> tq = em.createQuery(cq);
        List<Pet7> pets = tq.getResultList();
        for (Pet7 p : pets) {
            logger.debug(p.toString());
        }
    }

    public void showPetWithStringBasedCriteriaQuery() {
        logger.debug("begin show pet with string based CriteriaQuery");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);
        Root<Pet7> pet = cq.from(Pet7.class);
        cq.select(pet.get("name").as(String.class));
        cq.where(cb.equal(pet.get("name"), "pet1"));
        TypedQuery<String> tq = em.createQuery(cq);
        List<String> pets = tq.getResultList();
        for (String p : pets) {
            logger.debug(p);
        }
    }
}
