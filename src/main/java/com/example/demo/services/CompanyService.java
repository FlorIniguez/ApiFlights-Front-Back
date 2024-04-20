package com.example.demo.services;

import com.example.demo.exceptions.ResourcedNotFoundException;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public Optional<Company> updateCompany(Company company) {
        companyRepository.save(company);
        return companyRepository.findById((company.getId()));
    }

    public void deleteById(Long id) throws ResourcedNotFoundException {
        Company company = companyRepository.findById(id).orElseThrow(()-> new ResourcedNotFoundException("Company","Id", id));
        companyRepository.deleteById(company.getId());
    }
}
