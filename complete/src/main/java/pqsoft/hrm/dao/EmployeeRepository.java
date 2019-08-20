package pqsoft.hrm.dao;

import org.springframework.data.repository.CrudRepository;
import pqsoft.hrm.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
