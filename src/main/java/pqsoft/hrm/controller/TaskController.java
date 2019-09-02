package pqsoft.hrm.controller;

import com.google.common.base.Preconditions;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pqsoft.hrm.dao.EmployeeRepository;
import pqsoft.hrm.dao.TaskRepository;
import pqsoft.hrm.dto.TaskDto;
import pqsoft.hrm.dto.TaskSearchDto;
import pqsoft.hrm.model.Task;
import pqsoft.hrm.service.TaskService;
import pqsoft.hrm.util.SecurityUtils;

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
      @PageableDefault(page = 0, size = 2)
          @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
          })
          Pageable pageable) {

    prepareDataList(model, pageable, new TaskSearchDto());
    return "tasks";
  }

  private void prepareDataList(
      Model model,
      @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
          })
          @PageableDefault(page = 0, size = 2)
          Pageable pageable,
      final TaskSearchDto searchDto) {
    final Page<Task> tasks = taskService.search(pageable, searchDto);
    model.addAttribute("tasks", tasks);
    model.addAttribute("assignees", employeeRepos.findByAdmin(0));
    model.addAttribute("admin", SecurityUtils.getAdmin());

    // dto for search/add/edit
    model.addAttribute("searchDto", searchDto);
    model.addAttribute("newDto", new TaskDto());

    // paging
    model.addAttribute(
        "pageNumbers",
        IntStream.rangeClosed(1, tasks.getTotalPages()).boxed().collect(Collectors.toList()));
    model.addAttribute("currentPage", pageable.getPageNumber());
    model.addAttribute("totalPages", tasks.getTotalPages());
  }

  @RequestMapping(
    value = "/tasks/search",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  public String search(
      Model model,
      @PageableDefault(page = 0, size = 10)
          @SortDefault.SortDefaults({
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
            @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
          })
          Pageable pageable,
      @ModelAttribute("searchDto") TaskSearchDto searchDto) {
    prepareDataList(model, pageable, searchDto);
    return "tasks";
  }

  @RequestMapping(
      value = "/tasks/delete",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  public RedirectView delete(@RequestParam Integer id, @RequestParam Integer creator) {
    checkTaskOwner(creator);

    taskRepos.delete(id);
    return new RedirectView("/tasks");
  }

  @RequestMapping(
      value = "/tasks/add",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  public RedirectView create(@ModelAttribute("newDto") TaskDto input) {
    final Task task = new Task();
    task.setTaskName(input.getName());
    task.setDescription(input.getDescription());
    task.setStatus(Task.NEW);
    task.setCreatedAt(new Date());
    task.setUpdatedAt(new Date());

    int creator = SecurityUtils.getEmployeeId();
    task.setCreator(employeeRepos.findOne(creator));

    if (!CollectionUtils.isEmpty(input.getAssignees())) {
      task.setEmployees(
          StreamSupport.stream(employeeRepos.findAll(input.getAssignees()).spliterator(), false)
              .collect(Collectors.toList()));
    }

    taskRepos.save(task);
    return new RedirectView("/tasks");
  }

  @RequestMapping(
      value = "/tasks/update",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  public String update(@ModelAttribute("updateDto") TaskDto input) {
    if (Objects.isNull(input.getTaskId())) {
      throw new IllegalArgumentException("Invalid request to update task");
    }
    Task task = taskRepos.findOne(input.getTaskId());
    Preconditions.checkArgument(Objects.nonNull(task));

    int creator = task.getCreator().getId();

    checkTaskOwner(creator);

    task.setTaskName(input.getName());
    task.setDescription(input.getDescription());
    task.setStatus(input.getStatus());
    task.setUpdatedAt(new Date());

    if (!CollectionUtils.isEmpty(input.getAssignees())) {
      task.setEmployees(
          StreamSupport.stream(employeeRepos.findAll(input.getAssignees()).spliterator(), false)
              .collect(Collectors.toList()));
    }
    taskRepos.save(task);

    return "tasks";
  }

  private void checkTaskOwner(int creator) {
    boolean isAdmin = SecurityUtils.getAdmin() == 1;
    int employeeId = SecurityUtils.getEmployeeId();
    if (employeeId != creator && !isAdmin) {
      throw new IllegalArgumentException(
          "Don't have permission to update the task because you are not task owner or admin");
    }
  }
}
