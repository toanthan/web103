package pqsoft.hrm.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
  private Integer taskId;
  private String name;
  private String description;
  private String status;
  private List<Integer> assignees;
}
