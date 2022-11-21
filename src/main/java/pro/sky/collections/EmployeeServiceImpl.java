package pro.sky.collections;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.collections.Exception.EmployeeNotFoundException;
import pro.sky.collections.Exception.InvalidInputException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    public Map<String, Employee> employees =  new HashMap<>(Map.of(
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

        if(!validateEmployee(employee.getFirstName(), employee.getLastName())) {
            throw new InvalidInputException();
        }
        if(checkExistenceEmployee(employee.getFirstName(), employee.getLastName()))
        {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.takeFirstLastName(), employee);

    }
    @Override
    public void deleteEmployee(String firstName, String lastName) {

        if(!validateEmployee(firstName, lastName)) {
            throw new InvalidInputException();
        }
        if(!checkExistenceEmployee(firstName, lastName)) {
            throw new EmployeeNotFoundException();
        } else {
            employees.remove(firstName + " " + lastName);
        }
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return employees;
    }


    @Override
    public boolean checkExistenceEmployee(String firstName, String lastName) {
        if(!validateEmployee(firstName, lastName)) {
            throw new InvalidInputException();
        }
        return employees.containsKey(firstName + " " + lastName);
    }


    private boolean validateEmployee(String firstName, String lastName) {
        return isAlpha(firstName) && isAlpha(lastName);
    }


}
