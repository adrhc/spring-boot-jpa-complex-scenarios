package test_project.dto;

/**
 * Created by adriana on 01-Dec-15.
 */
public class PersonAndCountryName {
    private String person;
    private String country;

    public PersonAndCountryName(String person, String country) {
        this.person = person;
        this.country = country;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "PersonAndCountryName{" +
                "person='" + person + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
