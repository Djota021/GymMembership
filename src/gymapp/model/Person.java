package gymapp.model;

import java.io.Serializable;

public abstract class Person implements Serializable {

    protected String firstName;
    protected String lastName;
    protected String gender;

    public Person(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public abstract String getInfo();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getGender() {
        return gender;
    }
}
