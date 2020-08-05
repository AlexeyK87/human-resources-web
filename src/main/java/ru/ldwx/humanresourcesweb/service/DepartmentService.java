package ru.ldwx.humanresourcesweb.service;

import ru.ldwx.humanresourcesweb.model.Department;

import java.util.List;

public interface DepartmentService {
    Department create(Department department);

    void delete(int id);

    List<Department> getAll();

    Department get(String name);
}
