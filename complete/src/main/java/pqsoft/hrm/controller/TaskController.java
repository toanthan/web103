package pqsoft.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pqsoft.hrm.dao.TaskRepository;

@Controller
public class TaskController {
  private static final String template = "Hello, %s!";

  private TaskRepository employeeRepos;

  @GetMapping("/tasks")
  public String index(Model model) {
    return "tasks";
  }
}
