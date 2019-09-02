package pqsoft.hrm.sql;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class TaskJpaQueryBuilder implements JpaQueryBuilder {

  public String getSort(Pageable pageable) {
    return String.format(
        "ORDER BY %s",
        String.join(
            ", ",
            StreamSupport.stream(pageable.getSort().spliterator(), false)
                .map(item -> String.format("%s %s", item.getProperty(), item.getDirection()))
                .collect(Collectors.toList())));
  }
}
