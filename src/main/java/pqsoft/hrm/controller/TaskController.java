package pqsoft.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pqsoft.hrm.dao.EmployeeRepository;
import pqsoft.hrm.dao.TaskRepository;

@Controller
public class TaskController {
  private TaskRepository taskRepos;
  private EmployeeRepository employeeRepos;

  @Autowired
  public TaskController(TaskRepository taskRepos, EmployeeRepository employeeRepos) {
    this.taskRepos = taskRepos;
    this.employeeRepos = employeeRepos;
  }

  @GetMapping("/tasks")
  public String index(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {
    SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    model.addAttribute("tasks", taskRepos.findAll(pageable));
    model.addAttribute("assignees", employeeRepos.findByAdmin(0));
    return "tasks";
  }

  @PostMapping("/tasks/delete")
  public String delete(Model model) {
    model.addAttribute("tasks", taskRepos.findAll());
    model.addAttribute("assignees", employeeRepos.findByAdmin(0));
    return "tasks";
  }
}
