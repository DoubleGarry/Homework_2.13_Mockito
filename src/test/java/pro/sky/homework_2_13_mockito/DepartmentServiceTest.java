package pro.sky.homework_2_13_mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework_2_13_mockito.model.Employee;
import pro.sky.homework_2_13_mockito.services.DepartmentService;
import pro.sky.homework_2_13_mockito.services.EmployeeService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;
    private List<Employee> employees;


    @BeforeEach
    public void beforeEach() {
        employees = List.of(new Employee("Семен", "Семенов", 1, 10000));
        employees = List.of(new Employee("Петр", "Петров", 2, 20000));
        employees = List.of(new Employee("Иван", "Иванов", 3, 15000));
        employees = List.of(new Employee("Вася", "Васин", 3, 17000));
        Mockito.when(employeeService.findAll()).thenReturn(employees);
    }

    public
}
