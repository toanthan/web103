package pqsoft.hrm.dao;

import org.springframework.data.repository.CrudRepository;
import pqsoft.hrm.model.Employee;
import pqsoft.hrm.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
