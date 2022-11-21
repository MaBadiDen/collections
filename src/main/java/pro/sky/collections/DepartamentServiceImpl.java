package pro.sky.collections;

import jdk.internal.icu.text.UnicodeSet;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartamentServiceImpl implements DepartamentService {
    private final EmployeeService employeeService;

    public DepartamentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMaxSalary(int departamentId) {
        final List<Employee> employeesInDep = getPersonsByDepartament(departamentId);

        Optional<Employee> employee = employeesInDep.stream()
                .max(Comparator.comparingInt(Employee::getSalary));

        return employee.orElseThrow();
    }

    @Override
    public Employee getMinSalary(int departamentId) {
        final List<Employee> employeesInDep = getPersonsByDepartament(departamentId);

        Optional<Employee> employee = employeesInDep.stream()
                .min(Comparator.comparingInt(Employee::getSalary));
        return employee.orElseThrow();

    }

    @Override
    public List<Employee> writeEmployees() {
        int depId = 1;
        Map<String, Employee> employees = employeeService.getEmployees();
        while (getPersonsByDepartament(depId).size() == 0) {
            depId++;
        }
        List<Employee> employeesList = getPersonsByDepartament(depId);
        while (employeesList.size() != employees.size()) {
            final int finalDepId = depId++;
            List <Employee> newNewEmployees = getPersonsByDepartament(depId).stream()
                    .filter(e -> (e.getDepartament() != finalDepId))
                    .toList();
            employeesList.addAll(newNewEmployees);
        }
        return employeesList;
    }

    @Override
    public List<Employee> getPersonsByDepartament(int departamentId) {
        List<Employee> employees1 = new ArrayList<>();
        for(Employee employee : employeeService.getEmployees().values()) {
            if (employee.getDepartament() == departamentId) {
                employees1.add(employee);
            }
        }
        return employees1;
    }
}
