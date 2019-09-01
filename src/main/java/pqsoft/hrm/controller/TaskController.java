package pqsoft.hrm.controller;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pqsoft.hrm.dao.EmployeeRepository;
import pqsoft.hrm.dao.TaskRepository;
import pqsoft.hrm.service.TaskService;

@Controller
public class TaskController {
  private final TaskRepository taskRepos;
  private final EmployeeRepository employeeRepos;
  private final TaskService taskService;

  @Autowired
  public TaskController(
      TaskRepository taskRepos, EmployeeRepository employeeRepos, TaskService taskService) {
    this.taskRepos = taskRepos;
    this.employeeRepos = employeeRepos;
    this.taskService = taskService;
  }

  @GetMapping("/tasks")
  public String index(
      Model model,
      @PageableDefault(page = 0, size = 10)
          @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
          })
          Pageable pageable) {

    model.addAttribute("tasks", taskService.search(pageable, ImmutableMap.of()));
    model.addAttribute("assignees", employeeRepos.findByAdmin(0));
    return "tasks";
  }

  @PostMapping("/tasks/search")
  public String search(
      Model model,
      @PageableDefault(page = 0, size = 10)
          @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
          })
          Pageable pageable,
      @RequestBody Map<String, Object> params) {

    model.addAttribute("tasks", taskService.search(pageable, params));
    model.addAttribute("assignees", employeeRepos.findByAdmin(0));
    return "tasks";
  }

  @DeleteMapping("/tasks")
  public String delete(Model model) {
    return "tasks";
  }

  @PostMapping("/tasks/search")
  public String create(
      Model model,
      @PageableDefault(page = 0, size = 10)
      @SortDefault.SortDefaults({
          @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
          @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
      })
          Pageable pageable,
      @RequestBody Map<String, Object> params) {

    model.addAttribute("tasks", taskService.search(pageable, params));
    model.addAttribute("assignees", employeeRepos.findByAdmin(0));
    return "tasks";
  }
}
