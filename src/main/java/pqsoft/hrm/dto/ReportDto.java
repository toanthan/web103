package pqsoft.hrm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDto {
  private Integer reportId;
  private String title;
  private String description;
  private Integer taskId;
}
