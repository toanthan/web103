package pqsoft.hrm.sql;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class TaskJpaQueryBuilder implements JpaQueryBuilder {

  public ConditionQueryResult buildConditionQuery(MultiValueMap<String, String> params) {
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

  public String getSort(Pageable pageable) {
    return String.format(
        "ORDER BY %s",
        String.join(
            ", ",
            StreamSupport.stream(pageable.getSort().spliterator(), false)
                .map(item -> String.format("%s %s", item.getProperty(), item.getDirection()))
                .collect(Collectors.toList())));
  }

  public boolean hasValue(String name) {
    return false;
  }
}
