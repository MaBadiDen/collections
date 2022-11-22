package pro.sky.collections;

import jdk.internal.icu.text.UnicodeSet;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public Map<Integer, List<Employee>> writeEmployees() {
        return employeeService.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartament));
    }

    @Override
    public List<Employee> getPersonsByDepartament(int departamentId) {
        return employeeService.getEmployees().stream()
                .filter(e -> (e.getDepartament() == departamentId))
                .toList();
    }
}
