package pro.sky.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    void addEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Map<String, Employee> writeEmployee();

    boolean checkExistenceEmployee(Employee employee);
}
