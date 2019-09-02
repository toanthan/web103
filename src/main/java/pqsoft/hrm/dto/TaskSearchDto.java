package pqsoft.hrm.dto;

import com.google.common.collect.ImmutableMap;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;
import pqsoft.hrm.util.DateUtil;

@Getter
@Setter
public class TaskSearchDto implements SearchDto {
  private String name;
  private String status;
  private Integer assignee;
  private String fromDate;
  private String toDate;

  @Override
  public JsqlResult getJql() {
    final Map<String, Object> params = new HashMap<>();
    final List<String> conditions = new ArrayList<>();
    final List<String> joins = new ArrayList<>();
    if (Strings.isNotBlank(name)) {
      conditions.add("t.taskName LIKE :name");
      params.put("name", name + "%");
    }
    if (Strings.isNotBlank(status)) {
      conditions.add("t.status = :status");
      params.put("status", status);
    }
    if (Objects.nonNull(assignee)) {
      conditions.add("e.id = :assignee");
      joins.add("JOIN t.employees e");
      params.put("assignee", assignee);
    }
    if (Strings.isNotBlank(fromDate)) {
      conditions.add("t.createdAt >= :fromDate");
      params.put("fromDate", DateUtil.parse(fromDate));
    }
    if (Strings.isNotBlank(toDate)) {
      conditions.add("createdAt <= :toDate");
      params.put("toDate", DateUtil.parse(toDate));
    }
    if (CollectionUtils.isEmpty(conditions)) {
      return JsqlResult.of("", "", ImmutableMap.of());
    }

    return JsqlResult.of(
        String.join(" ", joins), "WHERE " + String.join(" AND ", conditions), params);
  }
}
