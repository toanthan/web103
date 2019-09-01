package pqsoft.hrm.service;

import java.util.Objects;
import org.springframework.stereotype.Service;
import pqsoft.hrm.model.Task;
import pqsoft.hrm.sql.JpaQueryBuilder;
import pqsoft.hrm.sql.TaskJpaQueryBuilder;

@Service
public class TaskService extends SearchService<Task> {
  private JpaQueryBuilder jpaQueryBuilder;

  public String getItemQuery() {
    return "FROM tasks";
  }

  public String getCountQuery() {
    return "SELECT COUNT(*) FROM tasks";
  }

  @Override
  public JpaQueryBuilder getJpaQueryBuilder() {
    if (Objects.isNull(jpaQueryBuilder)) {
      jpaQueryBuilder = TaskJpaQueryBuilder.of();
    }
    return jpaQueryBuilder;
  }
}
