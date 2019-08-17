package pqsoft.hrm;

public class Employee {

    private long id;
    private String firstName;
    private String lastName;

    public Employee(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
