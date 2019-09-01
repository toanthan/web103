package pqsoft.hrm.sql;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Pageable;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class TaskJpaQueryBuilder implements JpaQueryBuilder {
  private final Pageable pageable;
  private final Map<String, Object> params;

  public ConditionQueryResult buildConditionQuery() {
    final Map<String, Object> sqlParams = new HashMap<>();
    final StringBuilder builder = new StringBuilder();
    if (hasValue("sName")) {
      String name = params.get("name").toString();
      builder.append(String.format(" name = %s", ":name"));
    }
    if (hasValue("sStatus")) {
      String name = params.get("sStatus").toString();
      builder.append(String.format(" status = %s", ":status"));
    }
    if (hasValue("sAssignee")) {
      String name = params.get("status").toString();
      builder.append(String.format(" status = %s", ":status"));
    }
    if (hasValue("sCreatedDate")) {
      String name = params.get("status").toString();
      builder.append(String.format(" status = %s", ":status"));
    }
    return ConditionQueryResult.of(builder.toString(), sqlParams);
  }

  public String getSort() {
    return String.format(
        "ORDER BY %s",
        String.join(
            ", ",
            StreamSupport.stream(pageable.getSort().spliterator(), false)
                .map(item -> String.format("%s %s", item.getProperty(), item.getDirection()))
                .collect(Collectors.toList())));
  }

  public boolean hasValue(String name) {
    return params.containsKey(name) && Strings.isNotBlank(params.get(name).toString());
  }
}
