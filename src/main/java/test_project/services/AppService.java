package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_project.entities.*;
import test_project.entities.embeddable.EmployeeName;
import test_project.entities.embeddable.EmployeeName1;
import test_project.entities.embeddable.Period;
import test_project.entities.embeddable.VacationEntry;
import test_project.entities.scenario1.Phone1;
import test_project.enums.PhoneType;
import test_project.enums.ProjectType;
import test_project.util.PrintUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Created by adr on 11/10/15.
 */
@Service
@Transactional
public class AppService {
    private static final Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private PrintUtil printUtil;

    @Autowired
    private EntityManager em;

    public void persistPerson() {
        logger.debug("persistPerson");
        Person person = new Person();
        person.setFirstName("Homer");
        person.setLastName("Simpson");
        em.persist(person);
    }

    public void persistGeeks() {
        logger.debug("persistGeeks");
        // join using geek's pk
        Geek geek = new Geek();
        geek.setFirstName("Gavin");
        geek.setLastName("Coffee");
        geek.setFavouriteProgrammingLanguage("Java");
        em.persist(geek);
        geek = new Geek();
        geek.setFirstName("Thomas");
        geek.setLastName("Micro");
        geek.setFavouriteProgrammingLanguage("C#");
        em.persist(geek);
        geek = new Geek();
        geek.setFirstName("Christian");
        geek.setLastName("Cup");
        geek.setFavouriteProgrammingLanguage("Java");
        em.persist(geek);
    }

    public void showGeeks() {
        logger.debug("begin showGeeks");
        TypedQuery<Geek> q = em.createQuery("from Geek", Geek.class);
        List<Geek> geeks = q.getResultList();
        logger.debug("geeks: {}", geeks.size());
        for (Geek g : geeks) {
            logger.debug(g.toString());
        }
    }

    public void persistGeekWithIdCardAndPhones() {
        logger.debug("persistGeekWithIdCardAndPhones");
        IdCard idCard = new IdCard();
        idCard.setIdNumber("cardId-123");
        idCard.setIssueDate(new Date());
        em.persist(idCard);

        Geek geek = new Geek();
        geek.setFirstName("Adr");
        geek.setLastName("Adr");
        geek.setFavouriteProgrammingLanguage("C++");
        geek.setIdCard(idCard);
        em.persist(geek);

        Phone phone = new Phone();
        phone.setNumber("0724234096");
        phone.setPerson(geek);
        em.persist(phone);

        Phone1 phone1 = new Phone1();
        phone1.setNumber("0724234096");
        phone1.setGeek(geek);
        em.persist(phone);
    }

    public void showGeekAdr() {
        logger.debug("begin showGeekAdr");
        TypedQuery<Geek> q = em.createQuery("from Geek g where g.firstName = 'Adr'", Geek.class);
        logger.debug(q.getSingleResult().toString());
    }

    public Geek getGeekById(Long id) {
        return em.find(Geek.class, id);
    }

    public Geek getGeekByFirstNameWithCriteria(String firstName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Geek> cq = cb.createQuery(Geek.class);
        Root<Geek> r = cq.from(Geek.class);
        cq.where(cb.equal(r.get("firstName"), firstName));
        return em.createQuery(cq).getSingleResult();
    }

    public void showPersons() {
        logger.debug("BEGIN showPersons");
        TypedQuery<Person> query = em.createQuery("from Person", Person.class);
        List<Person> persons = query.getResultList();
        logger.debug("persons: {}", persons.size());
        for (Person person : persons) {
            logger.debug(person.toString());
        }
    }

    public void saveProjects() {
        logger.debug("@ElementCollection + @Embeddable + @Enumerated");
        List<Geek> geeks = em.createQuery(
                "from Geek g where g.favouriteProgrammingLanguage = :fpl", Geek.class).setParameter("fpl", "Java").getResultList();
        Project project = new Project();
        project.setTitle("Java Project");
        project.setProjectPeriod(new Period());
        project.getProjectPeriod().setStartDate(new Date());
        project.getProjectPeriod().setEndDate(new Date());
        project.getBillingPeriods().add(project.getProjectPeriod().clone());
        project.setProjectType(ProjectType.FIXED);
        logger.debug("geeks: {}", geeks.size());
        for (Geek geek : geeks) {
//            project.getGeeks().add(geek); -> no effect on actual db saving because is the inverse side
            geek.getProjects().add(project);
        }
        em.persist(project);
    }

    public void showPersons12() {
        logger.debug("BEGIN showPersons12");
        logger.debug("showPerson 1");
        Person person1 = em.find(Person.class, 1L);
        logger.debug(person1.toString());
        logger.debug("showPerson 2");
        Person person2 = em.find(Person.class, 2L);
        logger.debug(person2.toString());
    }

    public void showGeekByIdUsingHQL(Long id) {
        logger.debug("BEGIN showGeekByIdUsingHQL");
        Geek geek = em.find(Geek.class, id);
        logger.debug(geek.toString());
    }

    public void saveEmployeeWithPhone() {
        logger.debug("@ElementCollection + @MapKeyColumn (String PHONE_TYPE)");
        // PHONE_TYPE is the column from EMP_PHONE table (the CollectionTable table)
        Employee employee = createEmployeeWithPhone("employee1", true);
        em.persist(employee);
    }

    public void saveDepartmentAndSeniorities() {
        logger.debug("@ElementCollection + @MapKeyJoinColumn (join with Employee for seniorities)");
        Employee employee = createEmployeeWithPhone("employee2", false);
        Department department = new Department();
        department.setName("department1");
        // seniority determines full employee update (when it's department is set) + emp_seniority inserts
        department.setEmployeeSeniority(employee, 1);
        employee.setDepartment(department);
        // Must persist employee before department in order to save
        // seniority property (there's no "cascade" possible).
        // After employee + department inserts and before emp_seniority insert
        // there'll be a full update on employee only when it's department is set.
        em.persist(employee);
        em.persist(department);
    }

    public void saveEmployeeWithPhoneTypeEnum() {
        logger.debug("@ElementCollection + @MapKeyJoinColumn (PhoneType enum)");
        // PHONE_TYPE is the column from EMP_PHONE table (the CollectionTable table)
        // this time considered as PhoneType enum and also as the map key
        Employee employee = createEmployeeWithPhone("employee3", false);
        employee.getPhoneNumbersWithEnum().put(PhoneType.Home, "employee3 Home");
        em.persist(employee);
    }

    public void saveEmployeeWithVacationEntry() {
        logger.debug("@ElementCollection + @AttributeOverride");
        Employee employee = createEmployeeWithPhone("employee4", false);
        employee.getVacationBookings().add(new VacationEntry(Calendar.getInstance(), 5));
        em.persist(employee);
    }

    public void saveProfessor() {
        logger.debug("@Embeddable used as @EmbeddedId");
        Professor emp = new Professor("US", 1);
        emp.setName("Tom");
        emp.setSalary(999L);
        em.persist(emp);
    }

    public void saveDepartmentAndEmployeesByName() {
        logger.debug("@OneToMany for Map<EmployeeName, Employee>");
        Department department = new Department();
        department.setName("department2");

        Employee employee = createEmployeeWithPhone("employee5", false);
        employee.setFirstName("firstName5");
        employee.setLastName("lastName5");
        // if not setting the department than employeesByName property will be useless
        employee.setDepartment(department);

        // all columns from EmployeeName exists also in Employee
        // EmployeeName columns are declared as non-insert and non-update
        Map<EmployeeName, Employee> employeesByName = new HashMap<>();
        employeesByName.put(
                new EmployeeName(employee.getFirstName() + "-embedded",
                        employee.getLastName() + "-embedded"), employee);
        department.setEmployeesByName(employeesByName);

        // must persist it before department in order to save
        // employeesByName property (when not using "cascade")
        em.persist(employee);
        // After inserting the department:
        // - ALL the employee's columns are updated when setting the employee's department to the department
        //       persisted! the columns for EmployeeName are practically updated after just persisted!
        // - the employee's columns denoted by EmployeeName are updated again (overwriting the ones just updated)!
        em.persist(department);
    }

    public void saveEmployeeWithEmbeddedName() {
        logger.debug("saveEmployeeWithEmbeddedName");
        EmployeeWithEmbeddedName employee = new EmployeeWithEmbeddedName();
        EmployeeName1 employeeName1 = new EmployeeName1("firstName5", "lastName5");
        employee.setEmployeeName1(employeeName1);
        em.persist(employee);
    }

    public void saveDepartmentAndEmployeesHavingEmbeddedName() {
        logger.debug("@OneToMany for Map<EmployeeName1, EmployeeWithEmbeddedName>");
        Department department = new Department();
        department.setName("department3");

        EmployeeWithEmbeddedName employee = new EmployeeWithEmbeddedName();
        EmployeeName1 employeeName1 = new EmployeeName1("firstName5", "lastName5");
        employee.setEmployeeName1(employeeName1);
        employee.setDepartment(department);

        // all columns from EmployeeName exists also in EmployeeWithEmbeddedName.employeeName
        // because EmployeeWithEmbeddedName.employeeName is an EmployeeName instance
        Map<EmployeeName1, EmployeeWithEmbeddedName> employeesByName = new HashMap<>();
        // on purpose I use a new EmployeeName instead of employee.employeeName
        // just to see if something wrong happens
        employeesByName.put(
                new EmployeeName1(employeeName1.getFirstName() + "-embedded",
                        employeeName1.getLastName() + "-embedded"), employee);
        department.setEmployeeWithEmbeddedNameMap(employeesByName);

        // Must persist it before department in order to save
        // employeeWithEmbeddedNameMap property (when not using "cascade").
        // Strange but the employee will be created with firstName and lastName null;
        // a @Basic(optional = false) on employeeName field will have no effect!
        em.persist(employee);
        // After inserting the department:
        // - ALL the EmployeeWithEmbeddedName's columns are updated when setting the employee's department to the
        //       department persisted! the columns for EmployeeName are practically updated after just persisted!
        // - the employee's columns denoted by EmployeeName1 are updated again (overwriting the ones just updated)!
        em.persist(department);
    }

    private Employee createEmployeeWithPhone(String name, boolean createPhone) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(new Double(Math.random()).longValue());
        if (createPhone) {
            employee.getPhoneNumbers().put("home", name + " home");
        }
        return employee;
    }
}
