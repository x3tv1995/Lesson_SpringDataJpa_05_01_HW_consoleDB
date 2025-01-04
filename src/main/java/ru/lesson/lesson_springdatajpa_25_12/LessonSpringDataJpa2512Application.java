package ru.lesson.lesson_springdatajpa_25_12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.lesson.lesson_springdatajpa_25_12.entity.Company;
import ru.lesson.lesson_springdatajpa_25_12.entity.Employee;
import ru.lesson.lesson_springdatajpa_25_12.repository.CompanyRepository;
import ru.lesson.lesson_springdatajpa_25_12.repository.EmployeeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class LessonSpringDataJpa2512Application {

    public static void main(String[] args) throws IOException {
        var context = SpringApplication.run(LessonSpringDataJpa2512Application.class, args);
        CompanyRepository companyRepository = context.getBean("companyRepository", CompanyRepository.class);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        while (true) {
            System.out.println("\n" + "Меню:");
            System.out.println("0.Выход");
            System.out.println("1.Создать компанию");
            System.out.println("2.Создать работников");
            System.out.println("3.Удаление компании");
            System.out.println("4.Обновление компании по Id");
            System.out.println("5.Обновление работника по Id");
            System.out.println("6.Удаление работника компании. По Id сотрудника");
            System.out.println("7.Вывести сотрудников с высокой зарплатой");

            System.out.print("Введите ваш выбор: ");

            try {
                choice = Integer.parseInt(bufferedReader.readLine());

                switch (choice) {
                    case 0:
                        System.out.println("Выход из программы.");
                        return;

                    case 1:
                        Company company = CompanyInputHandler.newCompany(bufferedReader);
                        companyRepository.save(company);
                        break;

                    case 2:
                        int idCompany = getIdInput(bufferedReader, companyRepository);

                        Company company1 = companyRepository.findById(idCompany).orElseThrow();
                        Employee employees = EmployeeInputHandler.newEmployee(bufferedReader);
                        company1.addEmployee(employees);
                        companyRepository.save(company1);
                        break;
                    case 3:
                        int idCompanyDelete = getIdInput(bufferedReader, companyRepository);
                        System.out.println("Удаление по Id компании.");
                        System.out.println("Введите  номер Id");
                        companyRepository.deleteById(idCompanyDelete);
                        break;

                    case 4:
                        int idCompanyForUpdate = getIdInput(bufferedReader, companyRepository);
                        System.out.println("Обновление по Id компании.");
                        CompanyService.updateCompany(idCompanyForUpdate, CompanyInputHandler.getCompanyName(bufferedReader),
                                CompanyInputHandler.getCompanyAddress(bufferedReader),
                                CompanyInputHandler.getCompanyFoundationDate(bufferedReader), companyRepository);
                        break;

                    case 5:
                        int employeeUpdateById = getIdInput(bufferedReader, companyRepository);
                        System.out.println("Обновление по Id сотрудника.");
                        EmployeeService.updateEmployee(employeeUpdateById, EmployeeInputHandler.getEmployeeName(bufferedReader),
                                EmployeeInputHandler.getEmployeePost(bufferedReader),
                                EmployeeInputHandler.getEmployeeSalary(bufferedReader), employeeRepository);
                        break;

                    case 6:
                        int employeeDeleteById = getIdInput(bufferedReader, companyRepository);
                        System.out.println("Удаление по Id сотрудника.");
                        employeeRepository.deleteById(employeeDeleteById);
                        break;

                    case 7:
                       int salary = EmployeeService.salaryEmployee(bufferedReader);
                       List<Company> bySalaryEmployee = companyRepository.findBySalaryEmployee(salary);
                        bySalaryEmployee.forEach(System.out::println);
                        break;


                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");

                }

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введено некорректное значение. Введите число!");
            }

        }


//		employeeRepository.deleteById(3);
//		employeeRepository.deleteById(4);
//		employeeRepository.deleteById(5);
//		employeeRepository.deleteById(6);


//	List<Company> listCompany =	companyRepository.findAllJoinEmployees();
//		for (Company company : listCompany) {
//			System.out.println(company.getCompanyName());
//			for (Employee employee : company.getEmployees()) {
//				System.out.println(employee);
//			}
//			System.out.println("\n\n");
//		}

//		Company company = new Company("Dom3","Moscow", LocalDate.of(1980,11,1));
//		Employee employee1 = new Employee("Dima","java dev",1000);
//		Employee employee2 = new Employee("Sany","java dev",14000);
//		Employee employee3 = new Employee("Sany3","java dev",14000);
//		Employee employee4 = new Employee("Sany4","java dev",14000);
//
//		company.addEmployee(employee1);
//		company.addEmployee(employee2);
//		company.addEmployee(employee3);
//		company.addEmployee(employee4);
//
//		companyRepository.save(company);


//		company.addEmployee(employee1);
//		company.addEmployee(employee2);
//		company.addEmployee(employee3);
//		company.addEmployee(employee4);
//		company.addEmployee(employee5);
//
//
//
//		Company company2 = new Company("Samsung","Moscow", LocalDate.of(1981,11,1));
//		Employee employee12 = new Employee("Lina2","java dev",1000);
//		Employee employee22 = new Employee("Jonh2","java dev",14000);
//		Employee employee33 = new Employee("Max2","security",500);
//		Employee employee44 = new Employee("Sabina2","server",11600);
//		Employee employee55 = new Employee("Stepan2","frontend dev",700);
//
//
//
//		company2.addEmployee(employee12);
//		company2.addEmployee(employee22);
//		company2.addEmployee(employee33);
//		company2.addEmployee(employee44);
//		company2.addEmployee(employee55);
//
//		List<Company> companies = new ArrayList<>();
//		companies.add(company);
//		companies.add(company2);
//
//
//		companyRepository.saveAll(companies);
////
//////		Company companyById = companyRepository.findById(2)
//////				.orElseThrow(()-> new RuntimeException("No company found"));
//////
//////		companyById.getEmployees().forEach(System.out::println);
////
////		//Read
////		System.out.println();
////		List<Company> bySalaryEmployee = companyRepository.findBySalaryEmployee(12_000);
////		bySalaryEmployee.forEach(System.out::println);
//
//
//////		//Update
////		updateCompany(1,"Huawei","SaratovCity Moskovaskaya 55", LocalDate.of(1994,1,10),companyRepository);
//
//
//
////		//Delete
////		companyRepository.deleteById(1);


    }














    public static int getIdInput(BufferedReader reader, CompanyRepository companyRepository) {
        int allIdCompany = (int) companyRepository.count();
        int number = 0;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print("Введите целое число id : ");
            try {
                String input = reader.readLine();
                if (input.matches("\\d+")) {
                    number = Integer.parseInt(input);
                    inputValid = true;
                } else if (number > allIdCompany || number < 0) {
                    System.out.println("Нет  с таким Id");
                    System.out.println("Введите от 0 до " + allIdCompany);
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




}


