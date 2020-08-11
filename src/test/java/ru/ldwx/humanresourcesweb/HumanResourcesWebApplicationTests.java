package ru.ldwx.humanresourcesweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ldwx.humanresourcesweb.web.DepartmentController;
import ru.ldwx.humanresourcesweb.web.EmployeeController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HumanResourcesWebApplicationTests {

    @Autowired
    DepartmentController departmentController;

    @Autowired
    EmployeeController employeeController;

    @Test
    void contextLoads() {
        assertThat(departmentController).isNotNull();
        assertThat(employeeController).isNotNull();
    }
}
