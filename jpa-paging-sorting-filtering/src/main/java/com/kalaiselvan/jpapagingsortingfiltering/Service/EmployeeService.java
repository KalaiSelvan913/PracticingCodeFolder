package com.kalaiselvan.jpapagingsortingfiltering.Service;

import com.kalaiselvan.jpapagingsortingfiltering.Model.Employee;
import com.kalaiselvan.jpapagingsortingfiltering.Model.EmployeePage;
import com.kalaiselvan.jpapagingsortingfiltering.Model.EmployeeSearchCriteria;
import com.kalaiselvan.jpapagingsortingfiltering.Repository.EmployeeCriteriaRepository;
import com.kalaiselvan.jpapagingsortingfiltering.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeCriteriaRepository employeeCriteriaRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeCriteriaRepository employeeCriteriaRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCriteriaRepository = employeeCriteriaRepository;
    }

    public Page<Employee> getEmployee(EmployeePage employeePage,
                                      EmployeeSearchCriteria employeeSearchCriteria) {
        return employeeCriteriaRepository.findAllWithFilters(employeePage, employeeSearchCriteria);
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
}
