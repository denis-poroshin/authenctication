package ru.authentication.springauthentication.interfaces;

import ru.authentication.springauthentication.entity.EmployeeEntity;
import java.util.Collection;


public interface EmployeeService {

    EmployeeEntity createEmployee(EmployeeEntity employeeEntity);

    void changeEmployee(Long id, EmployeeEntity employeeEntity);

    EmployeeEntity deleteEmployee(Long id);

    EmployeeEntity getEmployee(Long id);

    Collection<EmployeeEntity> getAllEmployees();

}