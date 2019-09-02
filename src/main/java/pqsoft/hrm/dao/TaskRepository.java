package pqsoft.hrm.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import pqsoft.hrm.model.Employee;
import pqsoft.hrm.model.Task;

import java.util.List;

public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {
	List<Task> findByCreator(Employee employee);
}
