package ru.lesson.lesson_springdatajpa_25_12;

import ru.lesson.lesson_springdatajpa_25_12.entity.Company;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CompanyInputHandler {

    public static Company newCompany(BufferedReader reader) throws IOException {
        String lineCreatName = null;
        String lineAdress = null;
        LocalDate date = null;


        while (true) {
            System.out.println("Введите");
            System.out.println("1.Напишите название компании");
            System.out.println("2.Адрес компании");
            System.out.println("3.Дата создании компании");

            String line = reader.readLine();
            switch (line) {
                case "1":
                    lineCreatName = getCompanyName(reader);
                    break;

                case "2":
                    lineAdress = getCompanyAddress(reader);
                    break;

                case "3":
                    date = getCompanyFoundationDate(reader);
                    break;
                default:
                    System.out.println("Неверный ввод, введите заново.");

            }
            if (lineCreatName != null && lineAdress != null && date != null) {
                break;
            }
        }
        return new Company(lineCreatName, lineAdress, date);


    }

    public static String getCompanyName(BufferedReader reader) throws IOException {
        String companyName = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print("Введите название компании: ");
            try {
                companyName = reader.readLine();
                if (companyName == null || companyName.trim().isEmpty()) {
                    System.out.println("Ошибка: Название компании не может быть пустым.");
                } else if (companyName.matches("\\d+")) {
                    System.out.println("Ошибка: Название компании не может состоять только из цифр.");
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

    public static String getCompanyAddress(BufferedReader reader) throws IOException {
        String companyAddress = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print("Введите адрес компании: ");
            try {
                companyAddress = reader.readLine();

                if (companyAddress == null || companyAddress.trim().isEmpty()) {
                    System.out.println("Ошибка: Адрес компании не может быть пустым.");
                } else {
                    inputValid = true;
                }

            } catch (IOException e) {
                System.err.println("Ошибка ввода/вывода: " + e.getMessage());
                throw e;
            }
        }
        return companyAddress.trim();
    }

    public static LocalDate getCompanyFoundationDate(BufferedReader reader) throws IOException {
        LocalDate foundationDate = null;
        boolean inputValid = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!inputValid) {
            System.out.print("Введите дату основания компании (в формате yyyy-MM-dd): ");
            try {
                String input = reader.readLine();
                foundationDate = LocalDate.parse(input, formatter);
                inputValid = true;

            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: Неверный формат даты. Пожалуйста, используйте формат yyyy-MM-dd.");
            } catch (NullPointerException | IOException e) {
                System.out.println("Ошибка: Неверный формат даты. Пожалуйста, используйте формат yyyy-MM-dd.");
                throw e;
            }
        }
        return foundationDate;
    }
}
