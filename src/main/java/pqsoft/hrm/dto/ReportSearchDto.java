package pqsoft.hrm.dto;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;
import pqsoft.hrm.util.DateUtil;

import java.util.*;

@Getter
@Setter
public class ReportSearchDto implements SearchDto {
  private String title;
  private Integer creator;
  private String fromDate;
  private String toDate;

  @Override
  public JsqlResult getJql() {
    final Map<String, Object> params = new HashMap<>();
    final List<String> conditions = new ArrayList<>();
    final List<String> joins = new ArrayList<>();
    if (Strings.isNotBlank(title)) {
      conditions.add("t.taskName LIKE :name");
      params.put("name", title + "%");
    }
    if (Objects.nonNull(creator)) {
      conditions.add("e.id = :assignee");
      joins.add("JOIN t.employees e");
      params.put("assignee", creator);
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
