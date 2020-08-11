package ru.ldwx.humanresourcesweb;



import ru.ldwx.humanresourcesweb.model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;

import static ru.ldwx.humanresourcesweb.DepartmentTestData.*;


public class EmployeeTestData {
    public static final int EMPLOYEE_ID = 10;

    public static final Employee EMPLOYEE_1 = new Employee(EMPLOYEE_ID, "Иванов Иван", LocalDate.parse("1987-01-01"), new BigDecimal("10000.00"), DEPARTMENT_1);
    public static final Employee EMPLOYEE_2 = new Employee(EMPLOYEE_ID + 1, "Петров Иван", LocalDate.parse("1989-03-12"), new BigDecimal("20000.00"), DEPARTMENT_1);
    public static final Employee EMPLOYEE_3 = new Employee(EMPLOYEE_ID + 2, "Сидоров Иван", LocalDate.parse("2000-12-01"), new BigDecimal("30000.00"), DEPARTMENT_1);
    public static final Employee EMPLOYEE_4 = new Employee(EMPLOYEE_ID + 3, "Смирнов Иван", LocalDate.parse("1995-01-06"), new BigDecimal("40000.00"), DEPARTMENT_1);
    public static final Employee EMPLOYEE_5 = new Employee(EMPLOYEE_ID + 4, "Петр Петров", LocalDate.parse("1985-04-01"), new BigDecimal("20000.00"), DEPARTMENT_2);
    public static final Employee EMPLOYEE_6 = new Employee(EMPLOYEE_ID + 5, "Семен Семенов", LocalDate.parse("1987-01-02"), new BigDecimal("30000.00"), DEPARTMENT_3);
    public static final Employee EMPLOYEE_7 = new Employee("Вальдемар Аттердаг", LocalDate.parse("2000-10-01"), new BigDecimal("5000.00"), DEPARTMENT_3);

}
