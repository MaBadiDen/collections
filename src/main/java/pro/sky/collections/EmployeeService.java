package pro.sky.collections;

import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);

    void deleteEmployee(String firstName, String lastName);

    List<Employee> writeEmployees();

    boolean checkExistenceEmployee(String firstName, String lastName);

    List<Employee> getPersonsByDepartament(int departamentId);

    Employee getMaxSalary(int departamentId);

    Employee getMinSalary(int departamentId);
}
