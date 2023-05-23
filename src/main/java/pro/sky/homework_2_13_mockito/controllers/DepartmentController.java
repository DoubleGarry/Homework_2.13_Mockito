package pro.sky.homework_2_13_mockito.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework_2_13_mockito.model.Employee;
import pro.sky.homework_2_13_mockito.services.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalaryFromDepartment(
            @RequestParam("department") int department) {
        return departmentService.findEmployeeWithMaxSalaryFromDepartment(department);
    }
    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalaryFromDepartment(
            @RequestParam("department") int department) {
        return departmentService.findEmployeeWithMinSalaryFromDepartment(department);
    }
    @GetMapping(value = "/all", params = "department")
    public List<Employee> findEmployeesFromDepartment(@RequestParam("department") int department) {
        return departmentService.findAllEmployeesFromDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findEmployees() {
        return departmentService.findEmployeesByDepartment();
    }

}
