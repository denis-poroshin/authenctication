-- liquibase formatted sql

-- changeset dporoshin:1

CREATE TABLE employees(
                          id           SERIAL PRIMARY KEY,
                          nick         VARCHAR(60) UNIQUE NOT NULL,
                          first_name   VARCHAR(30),
                          last_name    VARCHAR(30),
                          surname      VARCHAR(30),
                          password     VARCHAR(30),
                          role         VARCHAR(20) UNIQUE

);

-- -- changeset dporoshin:2
--
-- INSERT INTO employees VALUES (
--                                  1, 'admin', 'myFirstName', 'myLastName', 'mySurname', 'admin', 'ROLE_ADMIN'
--                              ),
--                              (
--                                  2, 'user', 'myFirstName', 'myLastName', 'mySurname', 'user', 'ROLE_USER'
--                              );