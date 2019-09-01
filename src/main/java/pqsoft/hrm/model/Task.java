package pqsoft.hrm.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
@Entity(name = "tasks")
public class Task {
  private static final String NEW = "NEW";
  private static final String IN_PROGRESS = "DOING";
  private static final String DONE = "DONE";

  @Id
  @Column(name = "task_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "task_name")
  private String taskName;

  private String description;

  private String status;

  @Column(name = "begin_at")
  private Date beginAt;

  @Column(name = "finish_at")
  private Date finishAt;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  @ManyToOne
  @JoinColumn(name = "creator_id")
  private Employee creator;

  @ManyToMany
  @JoinTable(
    name = "employees_tasks",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "task_id")
  )
  private List<Employee> employees;

  @Transient
  public String getAssignees() {
    if (CollectionUtils.isEmpty(employees)) {
      return "";
    }
    return employees.stream().map(Employee::getAlias).collect(Collectors.joining());
  }
}
