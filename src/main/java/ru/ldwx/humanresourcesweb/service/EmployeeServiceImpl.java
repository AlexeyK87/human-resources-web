package ru.ldwx.humanresourcesweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ldwx.humanresourcesweb.model.Employee;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final RestTemplate template;

    @Value("${rest.employee.url}")
    private String REST_URL;

    @Autowired
    public EmployeeServiceImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public Employee create(Employee employee) {
        return template.postForObject(REST_URL, employee, Employee.class);
    }

    @Override
    public void delete(int id) {
        Map<String, String> params = Map.of("id", String.valueOf(id));
        template.delete(REST_URL + "/{id}", params);
    }

    @Override
    public Employee get(int id) {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromUriString(REST_URL);
        return template.getForObject(componentsBuilder.toUriString() + "/" + id, Employee.class);
    }

    @Override
    public void update(Employee employee) {
        template.postForObject(REST_URL, employee, Employee.class);
    }

    @Override
    public List<Employee> getAll() {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromUriString(REST_URL);
        Employee[] employees = template.getForObject(componentsBuilder.toUriString(), Employee[].class);
        return employees == null ? List.of() : Arrays.asList(employees);
    }

    @Override
    public List<Employee> getBetweenDates(LocalDate startDate, LocalDate endDate) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(REST_URL + "/filter")
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate);
        Employee[] employees = template.getForObject(builder.toUriString(), Employee[].class);
        return employees == null ? List.of() : Arrays.asList(employees);
    }
}
