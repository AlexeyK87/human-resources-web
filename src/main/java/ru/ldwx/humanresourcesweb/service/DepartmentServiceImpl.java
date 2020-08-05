package ru.ldwx.humanresourcesweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ldwx.humanresourcesweb.model.Department;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final RestTemplate template;

    @Value("${rest.department.url}")
    private String REST_URL;

    @Autowired
    public DepartmentServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public Department create(Department department) {
        return template.postForObject(REST_URL, department, Department.class);
    }

    @Override
    public void delete(int id) {
        Map<String, String> params = Map.of("id", String.valueOf(id));
        template.delete(REST_URL + "/{id}", params);
    }

    @Override
    public List<Department> getAll() {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromUriString(REST_URL);
        Department[] departments = template.getForObject(componentsBuilder.toUriString(), Department[].class);
        return departments == null ? List.of() : Arrays.asList(departments);
    }

    @Override
    public Department get(String name) {
        return template.getForObject(REST_URL + "/" + name, Department.class);
    }
}
