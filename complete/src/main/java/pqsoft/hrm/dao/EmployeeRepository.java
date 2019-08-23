package pqsoft.hrm.dao;

import org.springframework.data.repository.CrudRepository;
import pqsoft.hrm.model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	public List<Employee> findByAdmin(int admin);
}
