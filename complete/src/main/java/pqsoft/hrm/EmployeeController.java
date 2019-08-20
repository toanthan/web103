package pqsoft.hrm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class EmployeeController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/employees")
    public String employee(@RequestParam(value="name", defaultValue="World") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
