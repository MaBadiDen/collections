package pro.sky.collections;

import java.util.List;

public interface EmployeeService {

    void addEmployee(String firstName, String secondName);

    void deleteEmployee(String firstName, String lastName);

    List<Employee> writeEmployee();

    Integer checkExistenceEmployee(String firstName, String lastName);
}
