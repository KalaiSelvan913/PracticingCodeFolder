package com.kalaiselvan.jpapagingsortingfiltering.Repository;

import com.kalaiselvan.jpapagingsortingfiltering.Model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
