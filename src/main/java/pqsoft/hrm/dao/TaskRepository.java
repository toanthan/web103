package pqsoft.hrm.dao;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import pqsoft.hrm.model.Employee;
import pqsoft.hrm.model.Task;

public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {
  List<Task> findByCreator(Employee employee);
}
