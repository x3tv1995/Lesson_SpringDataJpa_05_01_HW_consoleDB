package ru.lesson.lesson_springdatajpa_25_12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lesson.lesson_springdatajpa_25_12.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("FROM Employee e WHERE e.post=:post")
    List<Employee> findEmployeeByPost(@Param("post") String post);

    @Query("FROM  Employee e WHERE e.post = :post  AND  e.salary > :salary")
    List<Employee> findEmployeeByPostAndSalary(@Param("post") String post, @Param("salary") int salary);

    @Query("FROM Employee e WHERE e.salary < :salary")
    List<Employee> findEmployeeBySalaryUnderThan(@Param("salary") int salary);




    @Query(
            value = "SELECT* FROM employee e WHERE e.id_company = ?1 OR e.id_company = ?2",
            nativeQuery = true)
    List<Employee> findEmployeeById(long id_1, long id_2);


    Employee findEmployeeByFirstName(String s);
}
