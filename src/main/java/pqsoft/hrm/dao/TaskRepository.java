package pqsoft.hrm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import pqsoft.hrm.model.Task;

public interface TaskRepository extends PagingAndSortingRepository<Task, Integer> {
  @Query("FROM task t WHERE t.creator.id = :userId OR t.assignees = :userId")
  Page<Task> findByUser(@Param("userId")int userId);
}
