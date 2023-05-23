package pro.sky.homework_2_13_mockito;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework_2_13_mockito.exceptions.EmployeeNotFoundException;
import pro.sky.homework_2_13_mockito.model.Employee;
import pro.sky.homework_2_13_mockito.services.DepartmentService;
import pro.sky.homework_2_13_mockito.services.EmployeeService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    public static Stream<Arguments> findEmployeeWithMaxSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Семен", "Семенов", 1, 10000)),
                Arguments.of(2, new Employee("Петр", "Петров", 2, 20000)),
                Arguments.of(3, new Employee("Вася", "Васин", 3, 17000))
        );
    }

    public static Stream<Arguments> findEmployeeWithMinSalaryFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Семен", "Семенов", 1, 10000)),
                Arguments.of(2, new Employee("Петр", "Петров", 2, 20000)),
                Arguments.of(3, new Employee("Иван", "Иванов", 3, 15000))
        );
    }

    public static Stream<Arguments> findAllEmployeesFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1,
                        Collections.singletonList
                                (
                                        new Employee("Семен", "Семенов", 1, 10000)
                                )
                ),
                Arguments.of(2,
                        Collections.singletonList(
                                new Employee("Петр", "Петров", 2, 20000)
                        )
                ),
                Arguments.of(3,
                        List.of(
                                new Employee("Иван", "Иванов", 3, 15000),
                                new Employee("Вася", "Васин", 3, 17000)
                        )
                ),
                Arguments.of(4,
                        Collections.emptyList()
                )
        );
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.when(employeeService.findAll()).thenReturn(
        List.of(
                new Employee("Семен", "Семенов", 1, 10000),
                new Employee("Петр", "Петров", 2, 20000),
                new Employee("Иван", "Иванов", 3, 15000),
                new Employee("Вася", "Васин", 3, 17000))

        );
    }

    @ParameterizedTest
    @MethodSource("findEmployeeWithMaxSalaryFromDepartmentTestParams")
    public void findEmployeeWithMaxSalaryFromDepartmentTest(int department, Employee expected) {
        Assertions.assertThat(departmentService.findEmployeeWithMaxSalaryFromDepartment(department))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("findEmployeeWithMinSalaryFromDepartmentTestParams")
    public void findEmployeeWithMinSalaryFromDepartmentTest(int department, Employee expected) {
        Assertions.assertThat(departmentService.findEmployeeWithMinSalaryFromDepartment(department))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("findAllEmployeesFromDepartmentTestParams")
    public void findAllEmployeesFromDepartmentTest(int department, List<Employee> expected) {
        Assertions.assertThat(departmentService.findAllEmployeesFromDepartment(department))
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void findEmployeesByDepartmentTest() {
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                Collections.singletonList(
                        new Employee("Семен", "Семенов", 1, 10000)
                ),
                2,
                Collections.singletonList(
                        new Employee("Петр", "Петров", 2, 20000)
                ),
                3,
                List.of(
                        new Employee("Иван", "Иванов", 3, 15000),
                        new Employee("Вася", "Васин", 3, 17000)
                )
        );
        Assertions.assertThat(departmentService.findEmployeesByDepartment())
                .containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    public void findEmployeeWithMaxSalaryNotFoundFromDepartmentTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMaxSalaryFromDepartment(4));
    }

    @Test
    public void findEmployeeWithMinSalaryNotFoundFromDepartmentTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMinSalaryFromDepartment(4));
    }
}
