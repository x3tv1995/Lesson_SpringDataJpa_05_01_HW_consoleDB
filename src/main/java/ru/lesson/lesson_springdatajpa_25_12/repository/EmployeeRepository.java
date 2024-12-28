package ru.lesson.lesson_springdatajpa_25_12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lesson.lesson_springdatajpa_25_12.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
