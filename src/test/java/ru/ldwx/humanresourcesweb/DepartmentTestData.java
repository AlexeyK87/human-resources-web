package ru.ldwx.humanresourcesweb;


import ru.ldwx.humanresourcesweb.model.Department;

public class DepartmentTestData {
    public static final int DEPARTMENT_ID = 1;

    public static final Department DEPARTMENT_1 = new Department(DEPARTMENT_ID, "Департамент информационных технологий");
    public static final Department DEPARTMENT_2 = new Department(DEPARTMENT_ID + 1, "Департамент персонала");
    public static final Department DEPARTMENT_3 = new Department(DEPARTMENT_ID + 2, "Департамент продаж");
    public static final Department DEPARTMENT_4 = new Department(DEPARTMENT_ID + 3, "Департамент предоставления услуг населению");
    public static final Department DEPARTMENT_5 = new Department(DEPARTMENT_ID + 4, "Департамент снабжения");
    public static final Department DEPARTMENT_6 = new Department(DEPARTMENT_ID + 5, "Департамент логистики");
    public static final Department DEPARTMENT_7 = new Department(DEPARTMENT_ID + 6, "Департамент для удаления");
    public static final Department DEPARTMENT_8 = new Department(null, "Новый департамент");

}
