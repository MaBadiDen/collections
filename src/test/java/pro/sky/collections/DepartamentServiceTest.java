package pro.sky.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartamentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartamentServiceImpl departamentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Oleg", "Olegov", 12000, 1),
                new Employee("Kostya", "Kostin", 122000, 1),
                new Employee("Masha", "Mashina", 1200, 2),
                new Employee("Pasha", "Pashina", 1223000, 2)
        );
        when(employeeService.getEmployees()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("paramsMaxSalary")
    public void positiveMaxSalaryTest(int depId, Employee employee) {
        assertThat(departamentService.getMaxSalary(depId)).isEqualTo(employee);
    }

    @Test
    public void negativeMaxSalaryTest() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> departamentService.getMaxSalary(3));
    }

    public static Stream<Arguments> paramsMaxSalary() {
        return Stream.of(
                Arguments.of(1, new Employee("Kostya", "Kostin", 122000, 1)),
                Arguments.of(2, new Employee("Pasha", "Pashina", 1223000, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("paramsMinSalary")
    public void positiveMinSalaryTest(int depId, Employee employee) {
        assertThat(departamentService.getMinSalary(depId)).isEqualTo(employee);
    }

    @Test
    public void negativeMinSalaryTest() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> departamentService.getMinSalary(3));
    }

    public static Stream<Arguments> paramsMinSalary() {
        return Stream.of(
                Arguments.of(1, new Employee("Oleg", "Olegov", 12000, 1)),
                Arguments.of(2, new Employee("Masha", "Mashina", 1200, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("paramsDepEmployees")
    public void getEmployeesByDepTest(int depId, List<Employee> employees) {
        assertThat(departamentService.getPersonsByDepartament(depId)).isEqualTo(employees);
    }

    public static Stream<Arguments> paramsDepEmployees() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee("Oleg", "Olegov", 12000, 1), new Employee("Kostya", "Kostin", 122000, 1))),
                Arguments.of(2, List.of(new Employee("Masha", "Mashina", 1200, 2), new Employee("Pasha", "Pashina", 1223000, 2))),
                Arguments.of(3, Collections.emptyList())
        );
    }

    @Test
    public void getAllEmployeesTest() {
        assertThat(departamentService.writeEmployees()).containsAllEntriesOf(
                Map.of(
                        1, List.of(new Employee("Oleg", "Olegov", 12000, 1), new Employee("Kostya", "Kostin", 122000, 1)),
                        2, List.of(new Employee("Masha", "Mashina", 1200, 2), new Employee("Pasha", "Pashina", 1223000, 2))
                )
        );
    }




}
