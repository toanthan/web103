package pqsoft.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String employee(@RequestParam(value="name", defaultValue="World") String name,
                           Model model) {
        final Employee employee = this.employeeRepos.findById(1L).get();
        model.addAttribute("name", name);
        model.addAttribute("phone", employee.getPhone());
        return "greeting";
    }
}
