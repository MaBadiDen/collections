package pro.sky.collections;


import org.springframework.stereotype.Service;
import pro.sky.collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.collections.Exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> employees =  new HashMap<>(Map.of(
            "Mark Potato",
            new Employee("Mark", "Potato", 10000, 1),
            "Oleg Melon",
            new Employee("Oleg", "Melon", 20000, 2),
            "Pasha Tapok",
            new Employee("Pasha", "Tapok", 11111, 1),
            "Kolya Limon",
            new Employee("Kolya", "Limon", 123412, 1),
            "Even Afrika",
            new Employee("Even", "Afrika", 23231, 2),
            "Stema Karta",
            new Employee("Stema", "Karta", 33333, 2)
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
    public String writeEmployees() {
        StringBuilder output = new StringBuilder("Список сотрудников: ");
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            Employee employee =     entry.getValue();
            output.append("<br>").append(employee.getFirstName()).append(" ").append(employee.getLastName()).append(" имеет зарплату: ").append(employee.getSalary()).append(" и работает в департаменте с номером ").append(employee.getDepartament()).append("<br>");
        }

        return output.toString();
    }
    @Override
    public boolean checkExistenceEmployee(String firstName, String lastName) {
        return employees.containsKey(firstName + " " + lastName);
    }


}
