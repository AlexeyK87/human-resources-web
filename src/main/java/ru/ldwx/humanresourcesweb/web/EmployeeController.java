package ru.ldwx.humanresourcesweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ldwx.humanresourcesweb.model.Department;
import ru.ldwx.humanresourcesweb.model.Employee;
import ru.ldwx.humanresourcesweb.service.DepartmentService;
import ru.ldwx.humanresourcesweb.service.EmployeeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    private final EmployeeService service;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService service, DepartmentService departmentService) {
        this.service = service;
        this.departmentService = departmentService;
    }

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/employees")
    public String getAllUsers(Model model) {
        model.addAttribute("employees", service.getAll());
        return "employeeList";
    }

    @RequestMapping(value = "/employees/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") Integer id) {
        service.delete(id);
        return "redirect:/employees";
    }

    @PostMapping("/employees/add")
    public String create(Employee employee) {
        Department department = departmentService.get(employee.getDepartment().getName());
        employee.setDepartment(department);
        service.create(employee);
        return "redirect:/employees";
    }

    @RequestMapping("/employees/add")
    public String getEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        return "employeeForm";
    }

    @RequestMapping("/report")
    public String getReport(Model model) {
        Map<String, BigDecimal> departmentAverageSalary = new HashMap<>();
        List<Employee> employees = service.getAll();
        Map<String, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(e -> e.getDepartment().getName()));
        for (Map.Entry<String, List<Employee>> entry : collect.entrySet()) {
            BigDecimal sum = new BigDecimal(0);
            for (Employee e : entry.getValue()) {
                sum = sum.add(e.getSalary());
            }
            BigDecimal average = sum.divide(new BigDecimal(entry.getValue().size()));
            departmentAverageSalary.put(entry.getKey(), average);
        }
        model.addAttribute("averageSalary", departmentAverageSalary);
        return "departmentReport";
    }
}
