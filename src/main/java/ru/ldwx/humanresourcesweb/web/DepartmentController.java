package ru.ldwx.humanresourcesweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ldwx.humanresourcesweb.model.Department;
import ru.ldwx.humanresourcesweb.service.DepartmentService;

@Controller
public class DepartmentController {
    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @RequestMapping("/departments")
    public String getAllDepartments(Model model) {
        model.addAttribute("departments", service.getAll());
        return "departmentList";
    }

    @RequestMapping(value = "/departments/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") Integer id) {
        service.delete(id);
        return "redirect:/departments";
    }

    @PostMapping("/departments/add")
    public String create(Department department) {
        service.create(department);
        return "redirect:/departments";
    }

    @RequestMapping("/departments/add")
    public String getDepartmentForm(Model model) {
        Department department = new Department("Новый отдел");
        model.addAttribute("department", department);
        return "departmentForm";
    }
}
