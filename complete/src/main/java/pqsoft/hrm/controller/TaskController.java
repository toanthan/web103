package pqsoft.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pqsoft.hrm.dao.TaskRepository;

@Controller
public class TaskController {
  private TaskRepository taskRepos;

  @Autowired
  public TaskController(TaskRepository taskRepos) {
    this.taskRepos = taskRepos;
  }

  @GetMapping("/tasks")
  public String index(Model model) {
    return "tasks";
  }
}
