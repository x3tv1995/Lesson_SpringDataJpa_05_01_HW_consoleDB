package ru.lesson.lesson_springdatajpa_25_12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.lesson.lesson_springdatajpa_25_12.entity.Company;
import ru.lesson.lesson_springdatajpa_25_12.entity.Employee;
import ru.lesson.lesson_springdatajpa_25_12.repository.CompanyRepository;
import ru.lesson.lesson_springdatajpa_25_12.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class LessonSpringDataJpa2512Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(LessonSpringDataJpa2512Application.class, args);
		CompanyRepository companyRepository = context.getBean("companyRepository", CompanyRepository.class);
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);


		Company company = new Company("Google","Moscow", LocalDate.of(1980,11,1));

		Employee employee1 = new Employee("Lina","java dev",1000);
		Employee employee2 = new Employee("Jonh","java dev",14000);
		Employee employee3 = new Employee("Max","security",500);
		Employee employee4 = new Employee("Sabina","server",11600);
		Employee employee5 = new Employee("Stepan","frontend dev",700);

		companyRepository.save(company);

		company.addEmployee(employee1);
		company.addEmployee(employee2);
		company.addEmployee(employee3);
		company.addEmployee(employee4);
		company.addEmployee(employee5);



		//Create
		companyRepository.save(company);
//
////		Company companyById = companyRepository.findById(2)
////				.orElseThrow(()-> new RuntimeException("No company found"));
////
////		companyById.getEmployees().forEach(System.out::println);
//
//		//Read
//		System.out.println();
//		List<Company> bySalaryEmployee = companyRepository.findBySalaryEmployee(12_000);
//		bySalaryEmployee.forEach(System.out::println);


////		//Update
//		updateCompany(1,"Huawei","SaratovCity Moskovaskaya 55", LocalDate.of(1994,1,10),companyRepository);



//		//Delete
//		companyRepository.deleteById(1);


	}


	public static void updateCompany(int idOldCompany, String updateName,String updateAdress, LocalDate updateDateCreat, CompanyRepository companyRepository){
		Company companyUpdate = companyRepository.findById(idOldCompany)
				.orElseThrow(() -> new RuntimeException("Company not found with id: " + idOldCompany));
		companyUpdate.setCompanyName(updateName);
		companyUpdate.setCompanyAddress(updateAdress);
		companyUpdate.setDate(updateDateCreat);
		companyRepository.save(companyUpdate);
	}



}
