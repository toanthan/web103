package pqsoft.hrm.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public interface SearchDto {
  JsqlResult getJql();

  @Getter
  @Setter
  @AllArgsConstructor(staticName = "of")
  class JsqlResult {
    private String joinSql;
    private String conditionSql;
    private Map<String, Object> params;
  }
}
