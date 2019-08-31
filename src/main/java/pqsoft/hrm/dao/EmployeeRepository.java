package pqsoft.hrm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pqsoft.hrm.model.Employee;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
	List<Employee> findByAdmin(int admin);
}
