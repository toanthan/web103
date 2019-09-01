package pqsoft.hrm.service;

import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pqsoft.hrm.model.Task;
import pqsoft.hrm.sql.JpaQueryBuilder;
import pqsoft.hrm.sql.TaskJpaQueryBuilder;

@Service
public class TaskService extends SearchService<Task> {
  @PersistenceContext private EntityManager em;
  private JpaQueryBuilder jpaQueryBuilder;

  public String getItemQuery() {
    return "FROM task";
  }

  public String getCountQuery() {
    return "SELECT COUNT(*) FROM task";
  }

  @Override
  public JpaQueryBuilder getJpaQueryBuilder(Pageable pageable, Map<String, Object> params) {
    if (Objects.isNull(jpaQueryBuilder)) {
      jpaQueryBuilder = TaskJpaQueryBuilder.of(pageable, params);
    }
    return jpaQueryBuilder;
  }
}
