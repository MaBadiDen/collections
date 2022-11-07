package pro.sky.collections;


import org.springframework.stereotype.Service;
import pro.sky.collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.collections.Exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees =  new ArrayList<>(List.of(
            new Employee("Mark", "Potato"),
            new Employee("Oleg", "Melon")
    ));

    @Override
    public void addEmployee(String firstName, String lastName) {
        if(checkExistenceEmployee(firstName, lastName) != null)
        {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);

    }
    @Override
    public void deleteEmployee(String firstName, String lastName) {
        Integer employee = checkExistenceEmployee(firstName, lastName);
        if(employee == null) {
            throw new EmployeeNotFoundException();
        } else {
            int delete = employee;
            employees.remove(delete);
        }
    }

    @Override
    public List<Employee> writeEmployee() {
        return employees;
    }
    @Override
    public Integer checkExistenceEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if(firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName()))
            {
                return i;
            }
        }
        return null;
    }

}
