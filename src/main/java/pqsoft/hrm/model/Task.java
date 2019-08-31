package pqsoft.hrm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "tasks")
public class Task {
  private static final int NEW = 0;
  private static final int IN_PROGRESS = 1;
  private static final int DONE = 2;

  @Id
  @Column(name = "task_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "task_name")
  private String taskName;

  private String description;

  private int status;

  @Column(name = "begin_at")
  private Date beginAt;

  @Column(name = "finish_at")
  private Date finishAt;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

  @OneToOne(optional = false) private Employee creator;

  @ManyToMany
  @JoinTable(name = "employees_tasks",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "task_id")
  )
  private List<Employee> employees;
}
