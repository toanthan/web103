package pqsoft.hrm.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Task {
	private final static int NEW = 0;
	private final static int IN_PROGRESS = 1;
	private final static int DONE = 2;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;

	@Column(name = "task_name")
	private String taskName;

	private String description;

	private int status;
	private Date beginAt;
	private Date finishAt;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@OneToOne
	private Employee creator;

	@ManyToMany
	private List<Employee> assignees;

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

	public List<Employee> getAssignees() {
		return assignees;
	}

	public void setAssignees(List<Employee> assignees) {
		this.assignees = assignees;
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
