package ru.authentication.springauthentication.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.authentication.springauthentication.entity.EmployeeEntity;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class EmployeeDetails implements UserDetails {
    private final EmployeeEntity employee;

    public EmployeeDetails (EmployeeEntity employee){
        this.employee = employee;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(employee.getRole().split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getFirstName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}