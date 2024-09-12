package ru.authentication.springauthentication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import ru.authentication.springauthentication.entity.EmployeeEntity;
import ru.authentication.springauthentication.exception.DataEnteredIncorrectlyException;
import ru.authentication.springauthentication.exception.EmployeeNotFoundException;
import ru.authentication.springauthentication.interfaces.EmployeeService;
import ru.authentication.springauthentication.repository.EmployeeRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class EmployeeServiceIml implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(EmployeeServiceIml.class);

    @Autowired
    public EmployeeServiceIml(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        logger.info("Creating employee: {}", employee);

        try {
            checkEmployee(employee);
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employee.setRole(employee.getRole());
            employeeRepository.save(employee);
        }catch (DataEnteredIncorrectlyException e){
            throw new DataEnteredIncorrectlyException(e.getMessage());
        }
        logger.info("Employee: {} created", employee);
        return employee;
    }

    @Override
    public void changeEmployee(Long id, EmployeeEntity employeeEntity) {
        logger.info("changing fields about an employee: {} with id {}", employeeEntity, id);

        EmployeeEntity changeEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id));

        changeEmployee.setNick(employeeEntity.getNick());
        changeEmployee.setFirstName(employeeEntity.getFirstName());
        changeEmployee.setLastName(employeeEntity.getLastName());
        changeEmployee.setSurname(employeeEntity.getSurname());
        changeEmployee.setRole(employeeEntity.getRole());
        changeEmployee.setPassword(passwordEncoder.encode(employeeEntity.getPassword()));
        employeeRepository.save(changeEmployee);

        logger.info("Employee: {} changed with id: {}", employeeEntity, id);
    }

    @Override
    public EmployeeEntity deleteEmployee(Long id) {
        logger.info("Deleting employee with id: {}", id);

        EmployeeEntity removedEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id));
        employeeRepository.delete(removedEmployee);

        logger.info("Employee: {} deleted", removedEmployee);
        return removedEmployee;
    }

    @Override
    public EmployeeEntity getEmployee(Long id) {
        logger.info("Retrieving employee with id: {}", id);

        return employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id));
    }

    @Override
    public Collection<EmployeeEntity> getAllEmployees() {
        logger.info("Retrieving all employees");

        return Collections.unmodifiableCollection(employeeRepository.findAll());
    }
    private void checkEmployee(EmployeeEntity employee) {
        if (employee.getNick().isEmpty()){
            throw new DataEnteredIncorrectlyException("Nick: %s is empty".formatted(employee.getNick()));
        }

        Optional<EmployeeEntity> byNick = employeeRepository.findByNick(employee.getNick());
        if (byNick.isPresent()){
            throw new DataEnteredIncorrectlyException("Nick: %s already exists".formatted(employee.getNick()));
        }


    }


}

