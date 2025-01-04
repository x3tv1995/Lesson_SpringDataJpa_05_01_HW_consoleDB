package ru.lesson.lesson_springdatajpa_25_12.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lesson.lesson_springdatajpa_25_12.entity.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c JOIN c.employees e where e.salary > :salary")
    List<Company> findBySalaryEmployee(@Param("salary") Integer salary);

//    @EntityGraph(attributePaths = "employees")
//    List<Company> findAll();


    @Query("SELECT c FROM Company c JOIN FETCH c.employees")
    List<Company> findAllJoinEmployees();
}
