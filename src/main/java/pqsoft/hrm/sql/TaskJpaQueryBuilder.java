package pqsoft.hrm.sql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class TaskJpaQueryBuilder implements JpaQueryBuilder {
  private final Pageable pageable;
  private final Map<String, Object> params;

  public ConditionQueryResult buildConditionQuery() {
    final Map<String, Object> sqlParams = new HashMap<>();
    final StringBuilder builder = new StringBuilder();
    if(hasValue("sName")) {
      String name = params.get("name").toString();
      builder.append(String.format(" name = %s", ":name"));
    }
    if(hasValue("sStatus")) {
      String name = params.get("sStatus").toString();
      builder.append(String.format(" status = %s", ":status"));
    }
    if(hasValue("sAssignee")) {
      String name = params.get("status").toString();
      builder.append(String.format(" status = %s", ":status"));
    }
    if(hasValue("sCreatedDate")) {
      String name = params.get("status").toString();
      builder.append(String.format(" status = %s", ":status"));
    }
    return ConditionQueryResult.of(builder.toString(), sqlParams);
  }

  public String getSort() {
    final StringBuilder builder = new StringBuilder("ORDER BY ");
    pageable.getSort().forEach(item -> builder.append(String.format("%s %s", item.getProperty(), item.getDirection())));
    return builder.toString();
  }

  public boolean hasValue(String name) {
    return params.containsKey(name) && Strings.isNotBlank(params.get(name).toString());
  }

}
