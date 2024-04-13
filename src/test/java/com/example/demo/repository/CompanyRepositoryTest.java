package com.example.demo.repository;

import com.example.demo.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanyRepositoryTest {
@Autowired
    private CompanyRepository companyRepository;
    private Company company;

    @BeforeEach
    public void setup(){
        company = new Company("FlyBondi","aca iria banner", "urlPage");
    }

    @Test
    void saveCompanyTest(){
      Company company1 = companyRepository.save(company);
     assertThat(company1).isNotNull();
     assertThat(company1.getName()).isEqualTo("FlyBondi");
    }
    @Test
    void findAllCompaniesTest(){
        Company company1 = new Company("nombrePrueba", "banner", "url");
        //guardo 2 companias
        companyRepository.save(company);
        companyRepository.save(company1);
        List<Company> companyList = companyRepository.findAll();

        assertThat(companyList).isNotNull();
        assertThat(companyList.size()).isEqualTo(2);
    }


}