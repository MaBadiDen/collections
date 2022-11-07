package pro.sky.collections;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.collections.Exception.EmployeeNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String hello() {
        return "Добро пожаловать в БухУчетСотрудников!";
    }
    @GetMapping("/add")
    public String addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeAlreadyAddedException e) {
            return "Такой сотрудник уже есть";
        }
            return (new Employee(firstName, lastName)) + " добавлен";
    }

    @GetMapping("/return")
    public List<Employee> retirn() {
        return employeeService.writeEmployee();
    }
    @GetMapping("/find")
    public String findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Integer employee = employeeService.checkExistenceEmployee(firstName, lastName);
        if(employee != null) {
            return "Такой сотрудник уже есть под номером " + (employee+1);
        }
        return "Сотрудник не обнаружен";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return "Такой сотрудник не обнаружен";
        }
        return (new Employee(firstName, lastName)) + " удален";
    }
}
