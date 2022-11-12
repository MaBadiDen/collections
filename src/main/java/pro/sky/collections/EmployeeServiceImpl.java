package pro.sky.collections;


import org.springframework.stereotype.Service;
import pro.sky.collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.collections.Exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> employees =  new HashMap<>(Map.of(
            "Mark Potato",
            new Employee("Mark", "Potato"),
            "Oleg Melon",
            new Employee("Oleg", "Melon")
    ));

    @Override
    public void addEmployee(Employee employee) {
        if(checkExistenceEmployee(employee))
        {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.toString(), employee);

    }
    @Override
    public void deleteEmployee(Employee employee) {
        if(!checkExistenceEmployee(employee)) {
            throw new EmployeeNotFoundException();
        } else {
            employees.remove(employee.toString());
        }
    }

    @Override
    public Map<String, Employee> writeEmployee() {
        return employees;
    }
    @Override
    public boolean checkExistenceEmployee(Employee employee) {
        return employees.containsKey(employee.toString());
    }

}
