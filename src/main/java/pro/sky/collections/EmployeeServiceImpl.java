package pro.sky.collections;


import org.springframework.stereotype.Service;
import pro.sky.collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.collections.Exception.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> employees =  new HashMap<>(Map.of(
            "Mark Potato",
            new Employee("Mark", "Potato", 10000, 1),
            "Oleg Melon",
            new Employee("Oleg", "Melon", 20000, 2),
            "Pasha Tapok",
            new Employee("Pasha", "Tapok", 11111, 1),
            "Sasha Pryanik",
            new Employee("Sasha", "Pryanik", 3123123,3),
            "Kolya Limon",
            new Employee("Kolya", "Limon", 123412, 1),
            "Even Afrika",
            new Employee("Even", "Afrika", 23231, 2),
            "Stema Karta",
            new Employee("Stema", "Karta", 33333, 2),
            "Mark Twen",
            new Employee("Mark", "Twen", 36543, 7),
            "Lask Kirov",
            new Employee("Lask", "Kirov", 36543, 5)
    ));

    @Override
    public void addEmployee(Employee employee) {
        if(checkExistenceEmployee(employee.getFirstName(), employee.getLastName()))
        {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.takeFirstLastName(), employee);

    }
    @Override
    public void deleteEmployee(String firstName, String lastName) {
        if(!checkExistenceEmployee(firstName, lastName)) {
            throw new EmployeeNotFoundException();
        } else {
            employees.remove(firstName + " " + lastName);
        }
    }


    @Override
    public List<Employee> writeEmployees() {
        int depId = 1;
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
    public boolean checkExistenceEmployee(String firstName, String lastName) {
        return employees.containsKey(firstName + " " + lastName);
    }
    @Override
    public List<Employee> getPersonsByDepartament(int departamentId) {
        List<Employee> employees1 = new ArrayList<>();
        for(Employee employee : employees.values()) {
            if (employee.getDepartament() == departamentId) {
                employees1.add(employee);
            }
        }
        return employees1;
    }
    @Override
    public Employee getMaxSalary(int departamentId) {
        final List<Employee> employeesInDep = getPersonsByDepartament(departamentId);

        Optional<Employee> employee = Optional.of(employeesInDep.stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get());
        return employee.orElseThrow();
    }

    @Override
    public Employee getMinSalary(int departamentId) {
        final List<Employee> employeesInDep = getPersonsByDepartament(departamentId);

        Optional<Employee> employee = Optional.of(employeesInDep.stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .get());
        return employee.orElseThrow();

    }


}
