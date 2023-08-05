package com.kalaiselvan.jpapagingsortingfiltering.Repository;

import com.kalaiselvan.jpapagingsortingfiltering.Model.Employee;
import com.kalaiselvan.jpapagingsortingfiltering.Model.EmployeePage;
import com.kalaiselvan.jpapagingsortingfiltering.Model.EmployeeSearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public EmployeeCriteriaRepository(EntityManager entityManager) {
        this.entityManager  = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Employee> findAllWithFilters(EmployeePage employeePage,
                                             EmployeeSearchCriteria employeeSearchCriteria){
        CriteriaQuery<Employee> cq = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = cq.from(Employee.class);
        Predicate predicate = getPredicate(employeeRoot, employeeSearchCriteria);
        cq.where(predicate);
        setOrder(employeeRoot, cq, employeePage);
        TypedQuery<Employee> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult(employeePage.getPageNumber() * employeePage.getPageSize());
        typedQuery.setMaxResults(employeePage.getPageSize());

        Pageable pageable = getPageable(employeePage);

        long employeesCount = getEmployeesCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pageable, employeesCount);
    }

    private Predicate getPredicate(Root<Employee> employeeRoot,
                                   EmployeeSearchCriteria employeeSearchCriteria) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(employeeSearchCriteria.getFirstName())) {
            predicates.add(
                    criteriaBuilder.like(employeeRoot.get("firstName"),
                            "%"+employeeSearchCriteria.getFirstName()+"%")
            );
        }
        if(Objects.nonNull(employeeSearchCriteria.getLastName())) {
            predicates.add(
                    criteriaBuilder.like(employeeRoot.get("lastName"),
                            "%"+employeeSearchCriteria.getLastName()+"%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(Root<Employee> employeeRoot,
                          CriteriaQuery<Employee> cq,
                          EmployeePage employeePage) {
        if(employeePage.getSortDirection().equals(Sort.Direction.ASC)) {
            cq.orderBy(criteriaBuilder.asc(employeeRoot.get(employeePage.getSortBy())));
        } else {
            cq.orderBy(criteriaBuilder.desc(employeeRoot.get(employeePage.getSortBy())));
        }
    }

    private Pageable getPageable(EmployeePage employeePage) {
        Sort sort = Sort.by(employeePage.getSortDirection(), employeePage.getSortBy());
        return PageRequest.of(employeePage.getPageNumber(), employeePage.getPageSize(), sort);
    }

    private long getEmployeesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> countRoot = countQuery.from(Employee.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
