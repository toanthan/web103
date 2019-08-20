package pqsoft.hrm;

import java.util.*;

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return
                Objects.equals(firstName, employee.firstName) &&
                        Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public static void main(String[] args) {
        Employee empl1 = new Employee(1L, "test1", "test1");
        Employee empl2 = new Employee(2L, "test1", "test1");
        System.out.println(empl1.equals(empl2));

        List<Employee> empls = new ArrayList<>();
        empls.add(empl1);
        empls.add(empl2);

        System.out.printf("List size: %s", empls.size());
        System.out.printf("First item: %s%n", empls.get(1));

        Set<Employee> sets = new HashSet<>();
        sets.add(empl1);
        sets.add(empl2);
        System.out.printf("Set size: %s", sets.size());

        final Map<String, Employee> map = new HashMap<>();
        map.put("1", empl1);
        map.put("2", empl1);
        map.get("2");
    }
}
