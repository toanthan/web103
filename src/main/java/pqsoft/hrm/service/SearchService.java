package pqsoft.hrm.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pqsoft.hrm.sql.JpaQueryBuilder;

public abstract class SearchService<T> {
  @PersistenceContext private EntityManager em;

  public Page<T> search(final Pageable pageable, final Map<String, Object> params) {
    final JpaQueryBuilder jpaQueryBuilder = getJpaQueryBuilder(pageable, params);

    final JpaQueryBuilder.ConditionQueryResult condition = jpaQueryBuilder.buildConditionQuery();
    final Query countQuery =
        em.createQuery(String.format("%s %s", getCountQuery(), condition.getConditionSql()));
    condition.getParams().forEach(countQuery::setParameter);

    int total = Integer.parseInt(countQuery.getSingleResult().toString());
    if (total == 0) {
      return new PageImpl<>(Collections.emptyList(), pageable, total);
    }

    final String sort = jpaQueryBuilder.getSort();
    final Query itemQuery =
        em.createQuery(String.format("%s %s %s", getItemQuery(), condition.getConditionSql(), sort))
            .setMaxResults(pageable.getPageSize())
            .setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
    condition.getParams().forEach(itemQuery::setParameter);

    final List<T> topics = itemQuery.getResultList();
    return new PageImpl<T>(topics, pageable, total);
  }

  public abstract String getItemQuery();

  public abstract String getCountQuery();

  public abstract JpaQueryBuilder getJpaQueryBuilder(
      final Pageable pageable, final Map<String, Object> params);
}
