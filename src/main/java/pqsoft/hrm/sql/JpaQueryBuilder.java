package pqsoft.hrm.sql;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

public interface JpaQueryBuilder {

  @Getter
  @Setter
  @AllArgsConstructor(staticName = "of")
  class ConditionQueryResult {
    private String conditionSql;
    private Map<String, Object> params;
  }

  ConditionQueryResult buildConditionQuery(final MultiValueMap<String, String> params);

  String getSort(final Pageable pageable);
}
