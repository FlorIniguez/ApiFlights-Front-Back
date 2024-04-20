package com.example.demo.controller;

import com.example.demo.exceptions.ResourcedNotFoundException;
import com.example.demo.model.Company;
import com.example.demo.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("")
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
    public String deleteCompany(@PathVariable Long id){
        try{
            companyService.deleteById(id);
            return "Company successfully deleted";
        } catch (ResourcedNotFoundException e) {
            System.out.println(e.getMessage());
            return "Company not found";
        }
    }

}
