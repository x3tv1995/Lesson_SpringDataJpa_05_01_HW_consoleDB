package ru.lesson.lesson_springdatajpa_25_12;

import ru.lesson.lesson_springdatajpa_25_12.entity.Employee;

import java.io.BufferedReader;
import java.io.IOException;

public class EmployeeInputHandler {

    public static Employee newEmployee(BufferedReader reader) throws IOException {
        String lineCreatName = null;
        String linePost = null;
        int salary = 0;


        while (true) {
            System.out.println("Введите");
            System.out.println("1.Напишите имя работника");
            System.out.println("2.Напишите должность работника");
            System.out.println("3.Напишите зарплату работника");

            String line = reader.readLine();
            switch (line) {
                case "1":
                    lineCreatName = getEmployeeName(reader);
                    break;

                case "2":
                    linePost = getEmployeePost(reader);
                    break;

                case "3":
                    salary = getEmployeeSalary(reader);
                    break;
                default:
                    System.out.println("Неверный ввод, введите заново.");

            }
            if (lineCreatName != null && linePost != null && salary != 0) {
                break;
            }
        }
        return new Employee(lineCreatName, linePost, salary);


    }

    public static String getEmployeeName(BufferedReader reader) throws IOException {
        String companyName = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print("Введите имя работника: ");
            try {
                companyName = reader.readLine();
                if (companyName == null || companyName.trim().isEmpty()) {
                    System.out.println("Ошибка: Имя работника не может быть пустым.");
                } else if (companyName.matches("\\d+")) {
                    System.out.println("Ошибка: Имя работника не может состоять только из цифр.");
                } else {
                    inputValid = true;
                }

            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода: " + e.getMessage());
                throw e;
            }
        }
        return companyName.trim();
    }


    public static String getEmployeePost(BufferedReader reader) throws IOException {
        String employeePost = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print("Введите должность работника: ");
            try {
                employeePost = reader.readLine();
                if (employeePost == null || employeePost.trim().isEmpty()) {
                    System.out.println("Ошибка: должность работника не может быть пустым.");
                } else if (employeePost.matches("\\d+")) {
                    System.out.println("Ошибка: должность работника не может состоять только из цифр.");
                } else {
                    inputValid = true;
                }

            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода: " + e.getMessage());
                throw e;
            }
        }
        return employeePost.trim();
    }

    public static int getEmployeeSalary(BufferedReader reader) throws IOException {
        int employeeSalary = 0;
        boolean flag = false;

        while (!flag) {
            System.out.print("Введите зарплату работника: ");
            try {
                employeeSalary = Integer.parseInt(reader.readLine());
                if (employeeSalary < 0) {
                    System.out.println("Ошибка: зарплата работника не может быть 0.");

                } else {
                    flag = true;
                }

            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода: " + e.getMessage());
                throw e;
            }
        }
        return employeeSalary;
    }
}
