package pqsoft.hrm;

import org.junit.Assert;
import org.junit.Test;
import pqsoft.hrm.model.Employee;

public class EmployeeTest {
    @Test
    public void given_firstNameAndLastName_whenGetFullName_thenHaveCorrectStr() {
        // GIVEN
        final String firstName = "Dat";
        final String lastName = "Tran";

        // WHEN
        final String actual = new Employee(1L, firstName, lastName).getFullName();

        // THEN
        Assert.assertEquals("Dat Tran", actual);
    }
}
