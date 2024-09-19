package ru.authentication.springauthentication.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.authentication.springauthentication.entity.EmployeeEntity;
import ru.authentication.springauthentication.interfaces.EmployeeService;
import java.util.Collection;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.createEmployee(employee);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void changeEmployee(@PathVariable long id, @RequestBody EmployeeEntity employee) {
        employeeService.changeEmployee(id, employee);

    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EmployeeEntity deleteEmployee(@PathVariable long id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/{id}")
    public EmployeeEntity getEmployee(@PathVariable long id) {
        return employeeService.getEmployee(id);

    }
    @GetMapping
    public Collection<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/welcome")
    public String getHello(){
        return "Hello!";
    }


}