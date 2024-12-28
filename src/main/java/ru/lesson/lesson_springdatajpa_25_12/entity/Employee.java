package ru.lesson.lesson_springdatajpa_25_12.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Data

@NoArgsConstructor

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String post;
    private int salary;

    @ManyToOne
    @JoinColumn(name = "id_company")
    @ToString.Exclude
    private Company company;

    public Employee(String firstName, String post, int salary) {
        this.firstName = firstName;
        this.post = post;
        this.salary = salary;
    }
}
