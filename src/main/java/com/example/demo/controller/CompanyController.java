package com.example.demo.controller;

import com.example.demo.model.Company;
import com.example.demo.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/")
    public List<Company> getAllCompanies(){
        return  companyService.getAllCompanies();
    }
    @GetMapping("/{id}")
    public Optional<Company> searchCompanyId(@PathVariable  Long id){
        return companyService.findById(id);
    }
    @PostMapping("/add")
    public void createCompany(@RequestBody Company company){
        companyService.createCompany(company);
    }
    @PutMapping("/update")
    public Optional<Company> updateCompany(@RequestBody Company company){
        return companyService.updateCompany(company);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCompany(@PathVariable Long id){
        companyService.deleteById(id);
    }

}
