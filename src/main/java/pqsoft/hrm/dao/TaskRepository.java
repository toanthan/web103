package pqsoft.hrm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pqsoft.hrm.model.Task;

public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {}
