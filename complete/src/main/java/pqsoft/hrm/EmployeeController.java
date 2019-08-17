package pqsoft.hrm;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/employees")
    public Employee employee(@RequestParam(value="name", defaultValue="World") String name) {
        return new Employee(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
