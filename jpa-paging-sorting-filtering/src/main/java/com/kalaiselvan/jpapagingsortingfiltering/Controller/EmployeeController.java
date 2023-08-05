package com.kalaiselvan.jpapagingsortingfiltering.Controller;

import com.kalaiselvan.jpapagingsortingfiltering.Model.Employee;
import com.kalaiselvan.jpapagingsortingfiltering.Model.EmployeePage;
import com.kalaiselvan.jpapagingsortingfiltering.Model.EmployeeSearchCriteria;
import com.kalaiselvan.jpapagingsortingfiltering.Service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployee(EmployeePage employeePage,
                                                      EmployeeSearchCriteria employeeSearchCriteria) {
        return new ResponseEntity<>(employeeService.getEmployee(employeePage
                , employeeSearchCriteria), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }
}
