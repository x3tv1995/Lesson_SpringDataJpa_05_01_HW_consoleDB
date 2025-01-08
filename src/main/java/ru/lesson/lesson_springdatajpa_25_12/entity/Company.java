package ru.lesson.lesson_springdatajpa_25_12.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String companyName;
    private String companyAddress;
    private LocalDate date;

    @OneToMany(mappedBy = "company",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    public Company(String companyName, String companyAddress, LocalDate date) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.date = date;
    }

    public void addEmployee(Employee employee) {
        employee.setCompany(this);
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}
