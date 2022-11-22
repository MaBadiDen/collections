package pro.sky.collections;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    void addEmployee(Employee employee);

    void deleteEmployee(String firstName, String lastName);

    Collection<Employee> getEmployees();

    boolean checkExistenceEmployee(String firstName, String lastName);


}
