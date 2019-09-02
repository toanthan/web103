package pqsoft.hrm.dao;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import pqsoft.hrm.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
  List<Employee> findByAdmin(int admin);

  List<Employee> findByStatus(int status);

  Employee findByEmail(String email);
}
