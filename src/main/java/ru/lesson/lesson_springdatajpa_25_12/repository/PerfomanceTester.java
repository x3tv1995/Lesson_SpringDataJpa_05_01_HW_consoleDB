package ru.lesson.lesson_springdatajpa_25_12.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.lesson.lesson_springdatajpa_25_12.entity.Employee;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PerfomanceTester implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;


    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        //Employee user = employeeRepository.findEmployeeByFirstName("David_102840000");
         employeeRepository.findEmployeeBySalaryUnderThan(200000);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " mc");


    }
}
