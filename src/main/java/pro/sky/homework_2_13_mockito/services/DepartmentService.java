package pro.sky.homework_2_13_mockito.services;

import org.springframework.stereotype.Service;
import pro.sky.homework_2_13_mockito.exceptions.DepartmentNotFoundException;
import pro.sky.homework_2_13_mockito.exceptions.EmployeeNotFoundException;
import pro.sky.homework_2_13_mockito.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public int findSumSalaryFromDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int findMaxSalaryFromDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder())
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public int findMinSalaryFromDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder())
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> findAllEmployeesFromDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }
}
