package pqsoft.hrm.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Employee getCreator() {
    return creator;
  }

  public void setCreator(Employee creator) {
    this.creator = creator;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Date getBeginAt() {
    return beginAt;
  }

  public void setBeginAt(Date beginAt) {
    this.beginAt = beginAt;
  }

  public Date getFinishAt() {
    return finishAt;
  }

  public void setFinishAt(Date finishAt) {
    this.finishAt = finishAt;
  }
}
