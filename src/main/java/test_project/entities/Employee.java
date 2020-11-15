package test_project.entities;

import test_project.entities.embeddable.VacationEntry;
import test_project.enums.PhoneType;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by adr on 11/19/15.
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long salary;
    @Column(name = "F_NAME")
    private String firstName;
    @Column(name = "L_NAME")
    private String lastName;

    /**
     * create table emp_phone (
     * employee_id integer not null,
     * phone_num varchar(255),
     * phone_type varchar(255) not null,
     * primary key (employee_id, phone_type)
     * )
     */
    @ElementCollection
    @CollectionTable(name = "EMP_PHONE")
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name = "PHONE_NUM")
    private Map<String, String> phoneNumbers = new HashMap<>();

    /**
     * create table emp_phone_with_enum (
     * employee_id integer not null,
     * phone_num varchar(255),
     * phone_type varchar(255) not null,
     * primary key (employee_id, phone_type)
     * )
     */
    @ElementCollection
    @CollectionTable(name = "EMP_PHONE_WITH_ENUM")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name = "PHONE_NUM")
    private Map<PhoneType, String> phoneNumbersWithEnum = new HashMap<>();

    /**
     * create table vacation (
     * emp_id integer not null,
     * days_abs integer,
     * start_date date
     * )
     */
    // Using a targetClass instead of generics
    // @ElementCollection(targetClass=VacationEntry.class)
    @ElementCollection
    @CollectionTable(name = "VACATION",
            joinColumns = @JoinColumn(name = "EMP_ID"))
    @AttributeOverride(name = "daysTaken",
            column = @Column(name = "DAYS_ABS"))
    private Collection<VacationEntry> vacationBookings = new LinkedList<>();

    @ManyToOne
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Map<String, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map<String, String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Map<PhoneType, String> getPhoneNumbersWithEnum() {
        return phoneNumbersWithEnum;
    }

    public void setPhoneNumbersWithEnum(Map<PhoneType, String> phoneNumbersWithEnum) {
        this.phoneNumbersWithEnum = phoneNumbersWithEnum;
    }

    public Collection<VacationEntry> getVacationBookings() {
        return vacationBookings;
    }

    public void setVacationBookings(Collection<VacationEntry> vacationBookings) {
        this.vacationBookings = vacationBookings;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        StringBuffer aBuffer = new StringBuffer("Employee ");
        aBuffer.append(" id: ");
        aBuffer.append(id);
        aBuffer.append(" with dept: ");
        if (null != department) {
            aBuffer.append(department.getName());
        }
        aBuffer.append(" phoneNumbers: ");
        for (Map.Entry e : phoneNumbers.entrySet()) {
            aBuffer.append(e.getKey() + "[" + e.getValue() + "] ");
        }
        aBuffer.append(" phoneNumbersWithEnum: ");
        for (Map.Entry e : phoneNumbersWithEnum.entrySet()) {
            aBuffer.append(e.getKey() + "[" + e.getValue() + "] ");
        }
        return aBuffer.toString();
    }
}