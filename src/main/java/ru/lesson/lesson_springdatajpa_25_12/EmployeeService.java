package ru.lesson.lesson_springdatajpa_25_12;

import ru.lesson.lesson_springdatajpa_25_12.entity.Employee;
import ru.lesson.lesson_springdatajpa_25_12.repository.EmployeeRepository;

import java.io.BufferedReader;
import java.io.IOException;

public class EmployeeService {

    public static int salaryEmployee(BufferedReader reader) {

        int number = 0;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print("Введите целое число зарплату : ");
            try {
                String input = reader.readLine();
                if (input.matches("\\d+")) {
                    number = Integer.parseInt(input);
                    inputValid = true;
                } else {
                    System.out.println("Ошибка: Введите только цифры.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введенное число слишком большое или имеет неверный формат.");
            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода: " + e.getMessage());
            }
        }
        return number;
    }

    public static void updateEmployee(int idOldEmployee, String updateName, String updatePost, int salary, EmployeeRepository employeeRepository) {
        Employee employeeUpdate = employeeRepository.findById(idOldEmployee).orElseThrow(() -> new RuntimeException("Company not found with id: " + idOldEmployee));
        employeeUpdate.setFirstName(updateName);
        employeeUpdate.setPost(updatePost);
        employeeUpdate.setSalary(salary);
        employeeRepository.save(employeeUpdate);
    }
}
