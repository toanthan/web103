package pqsoft.hrm.service;

import java.util.Objects;
import org.springframework.stereotype.Service;
import pqsoft.hrm.model.Report;
import pqsoft.hrm.sql.JpaQueryBuilder;
import pqsoft.hrm.sql.TaskJpaQueryBuilder;

@Service
public class ReportService extends SearchService<Report> {
  private JpaQueryBuilder jpaQueryBuilder;

  public String getItemQuery() {
    return "FROM report r";
  }

  public String getCountQuery() {
    return "SELECT COUNT(*) FROM report r";
  }

  @Override
  public JpaQueryBuilder getJpaQueryBuilder() {
    if (Objects.isNull(jpaQueryBuilder)) {
      jpaQueryBuilder = TaskJpaQueryBuilder.of();
    }
    return jpaQueryBuilder;
  }
}
