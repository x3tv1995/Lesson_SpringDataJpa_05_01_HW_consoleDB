package ru.lesson.lesson_springdatajpa_25_12.repository;

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


    List<Company> findByCompanyAddress(String address);


    @Query("FROM Company c  WHERE c.companyName = :companyName")
    Company findCompanyByCompanyName(@Param("companyName") String companyName);


    @Query("SELECT c FROM Company c JOIN c.employees e GROUP BY c HAVING AVG(e.salary) > :minSalary")
    List<Company> findCompaniesWitchAverageSalaryGraterThan(@Param("minSalary")int minSalary);


    @Query(nativeQuery = true,value = "SELECT AVG(e.salary) FROM employee e where e.id_company = ?1")
    Integer findAverageSalaryEmployeeByIdCompany(long id);

}
