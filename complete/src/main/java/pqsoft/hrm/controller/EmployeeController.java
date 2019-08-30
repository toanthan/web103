package pqsoft.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pqsoft.hrm.dao.EmployeeRepository;
import pqsoft.hrm.model.Employee;

@Controller
public class EmployeeController {
  private static final String template = "Hello, %s!";

  private EmployeeRepository employeeRepos;

  @Autowired
  public EmployeeController(EmployeeRepository employeeRepos) {
    this.employeeRepos = employeeRepos;
  }

  @GetMapping("/employees")
  public String index(Model model) {
    final Employee employee = this.employeeRepos.findById(1).get();
    return "employees";
  }
}
