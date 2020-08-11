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
import java.time.LocalDate;
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

    @RequestMapping(value = "/", produces = "text/plain;charset=UTF-8")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/employees", produces = "text/plain;charset=UTF-8")
    public String getAllUsers(Model model) {
        LocalDate startDate = LocalDate.MIN;
        LocalDate endDate = LocalDate.MAX;
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("employees", service.getAll());
        return "employeeList";
    }

    @RequestMapping(value = "/employees/filter", produces = "text/plain;charset=UTF-8")
    public String getBetween(Model model, String startDate, String endDate) {
        LocalDate start = startDate == null || startDate.length() == 0 ? LocalDate.of(1900, 1, 1) : LocalDate.parse(startDate);
        LocalDate end = endDate == null || endDate.length() == 0 ? LocalDate.now() : LocalDate.parse(endDate);
        model.addAttribute("employees", service.getBetweenDates(start, end));
        return "employeeList";
    }

    @RequestMapping(value = "/employees/delete", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
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

    @RequestMapping(value = "/employees/add", produces = "text/plain;charset=UTF-8")
    public String getEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        return "employeeForm";
    }

    @RequestMapping(value = "/employees/update")
    public String getEmployeeUpdateForm(@RequestParam(name = "id") String id, Model model) {
        Employee employee = service.get(Integer.parseInt(id));
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.getAll());
        return "employeeForm";
    }

    @RequestMapping(value = "/report", produces = "text/plain;charset=UTF-8")
    public String getReport(Model model) {
        Map<String, BigDecimal> departmentAverageSalary = new HashMap<>();
        List<Employee> employees = service.getAll();
        List<Department> departments = departmentService.getAll();
        Map<String, List<Employee>> employeesByDepartments = employees.stream().collect(Collectors.groupingBy(e -> e.getDepartment().getName()));
        for (Department department : departments) {
            String departmentName = department.getName();
            BigDecimal average = new BigDecimal(0);
            if (employeesByDepartments.containsKey(departmentName)) {
                List<Employee> employeeInDepartment = employeesByDepartments.get(departmentName);
                BigDecimal sum = new BigDecimal(0);
                for (Employee e : employeeInDepartment) {
                    sum = sum.add(e.getSalary());
                }
                average = sum.divide(new BigDecimal(employeeInDepartment.size()));
            }
            departmentAverageSalary.put(departmentName, average);
        }
        model.addAttribute("averageSalary", departmentAverageSalary);
        return "departmentReport";
    }
}
