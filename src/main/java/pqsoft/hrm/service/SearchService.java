package pqsoft.hrm.service;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pqsoft.hrm.dto.SearchDto;
import pqsoft.hrm.sql.JpaQueryBuilder;

public abstract class SearchService<T> {
  @PersistenceContext private EntityManager em;

  public Page<T> search(final Pageable pageable, final SearchDto params) {
    final JpaQueryBuilder jpaQueryBuilder = getJpaQueryBuilder();

    final SearchDto.JsqlResult jsql = params.getJql();
    final Query countQuery =
        em.createQuery(
            String.format("%s %s %s", getCountQuery(), jsql.getJoinSql(), jsql.getConditionSql()));
    jsql.getParams().forEach(countQuery::setParameter);

    int total = Integer.parseInt(countQuery.getSingleResult().toString());
    if (total == 0) {
      return new PageImpl<>(Collections.emptyList(), pageable, total);
    }

    final String sort = jpaQueryBuilder.getSort(pageable);
    final Query itemQuery =
        em.createQuery(
                String.format(
                    "%s %s %s %s", getItemQuery(), jsql.getJoinSql(), jsql.getConditionSql(), sort))
            .setMaxResults(pageable.getPageSize())
            .setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
    jsql.getParams().forEach(itemQuery::setParameter);

    final List<T> topics = itemQuery.getResultList();
    return new PageImpl<T>(topics, pageable, total);
  }

  public abstract String getItemQuery();

  public abstract String getCountQuery();

  public abstract JpaQueryBuilder getJpaQueryBuilder();
}
