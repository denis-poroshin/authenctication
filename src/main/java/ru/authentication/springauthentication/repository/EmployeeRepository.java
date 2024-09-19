package ru.authentication.springauthentication.repository;


import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.authentication.springauthentication.entity.EmployeeEntity;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByNick(String nick);

}