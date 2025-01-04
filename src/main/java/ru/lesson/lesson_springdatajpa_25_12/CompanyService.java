package ru.lesson.lesson_springdatajpa_25_12;

import ru.lesson.lesson_springdatajpa_25_12.entity.Company;
import ru.lesson.lesson_springdatajpa_25_12.repository.CompanyRepository;

import java.time.LocalDate;

public class CompanyService {

    public static void updateCompany(int idOldCompany, String updateName, String updateAdress, LocalDate updateDateCreat, CompanyRepository companyRepository) {
        Company companyUpdate = companyRepository.findById(idOldCompany)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + idOldCompany));
        companyUpdate.setCompanyName(updateName);
        companyUpdate.setCompanyAddress(updateAdress);
        companyUpdate.setDate(updateDateCreat);
        companyRepository.save(companyUpdate);
    }
}
