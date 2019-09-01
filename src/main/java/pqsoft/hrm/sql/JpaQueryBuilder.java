package pqsoft.hrm.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public interface JpaQueryBuilder {

  @Getter
  @Setter
  @AllArgsConstructor(staticName = "of")
  public static class ConditionQueryResult {
    private String conditionSql;
    private Map<String, Object> params;
  }

  ConditionQueryResult buildConditionQuery();

  String getSort();
}
