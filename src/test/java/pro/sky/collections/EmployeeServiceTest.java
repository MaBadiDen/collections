package pro.sky.collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.collections.Exception.InvalidInputException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EmployeeServiceTest {
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();



    @ParameterizedTest
    @MethodSource("testEmployees")
    public void positiveAddTest(String firstName, String lastName, int salary, int departament) {
        Employee expected = new Employee(firstName, lastName, salary, departament);
        assertThat(employeeService.getEmployees()).isEmpty();
        assertThat(employeeService.addEmployee(firstName, lastName, salary, departament)).isEqualTo(expected);
        assertThat(employeeService.getEmployees()).hasSize(1);

    }

    @ParameterizedTest
    @MethodSource("testEmployees")
    public void negativeAddTest1(String firstName, String lastName, int salary, int departament) {
        employeeService.addEmployee(firstName, lastName, salary, departament);
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee(firstName, lastName, salary, departament));
    }

    @Test
    public void negativeAddTest2() {
        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> employeeService.addEmployee("Oleg1", "Edit", 12331, 1));
        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> employeeService.addEmployee("Oleg", "Edit1", 12331, 1));
        assertThatExceptionOfType(InvalidInputException.class);
    }

    @ParameterizedTest
    @MethodSource("testEmployees")
    public void positiveDelete(String firstName, String lastName, int salary, int departament) {
        Employee expected = new Employee(firstName, lastName, salary, departament);
        assertThat(employeeService.getEmployees()).isEmpty();
        employeeService.addEmployee(firstName, lastName, salary, departament);
        assertThat(employeeService.getEmployees()).hasSize(1);
        assertThat(employeeService.deleteEmployee(firstName, lastName)).isEqualTo(expected);
        assertThat(employeeService.getEmployees()).isEmpty();
    }
    @ParameterizedTest
    @MethodSource("testEmployees")
    public void positiveCheckExistence(String firstName, String lastName, int salary, int departament) {
        Employee expected = new Employee(firstName, lastName, salary, departament);
        assertThat(employeeService.getEmployees()).isEmpty();
        employeeService.addEmployee(firstName, lastName, salary, departament);
        assertThat(employeeService.getEmployees()).hasSize(1);
        assertThat(employeeService.checkExistenceEmployee(firstName, lastName)).isEqualTo(true);
    }

    @Test
    public void negativeCheckExistence() {
        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> employeeService.checkExistenceEmployee("Oleg1", "Pavlov"));

        assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> employeeService.checkExistenceEmployee("Oleg", "Pavlov1"));
    }


    public static Stream<Arguments> testEmployees() {
        return Stream.of(
                Arguments.of("Oleg", "Olegov", 12000, 1),
                Arguments.of("Kostya", "Kostin", 122000, 2),
                Arguments.of("Masha", "Mashina", 1200, 2),
                Arguments.of("Pasha", "Pashina", 1223000, 3)
        );
    }
}
