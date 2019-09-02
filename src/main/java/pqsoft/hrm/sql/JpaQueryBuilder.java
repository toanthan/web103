package pqsoft.hrm.sql;

import org.springframework.data.domain.Pageable;

public interface JpaQueryBuilder {
  String getSort(final Pageable pageable);
}
